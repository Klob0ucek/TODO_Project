package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.action.PlaceholderAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.QuitAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ImportAction;

import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JToolBar;
import javax.swing.JComponent;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class ToolBarManager {

    private final static Dimension MODIFY_TOOLS_OFFSET = new Dimension(0, 35);
    private final static Dimension QUIT_OFFSET = new Dimension(0, 10);
    private final static Dimension SEPARATOR = new Dimension(0, 3);

    private final static Action[] MODIFY_PLACEHOLDERS = new Action[ActionType.values().length];

    static {
        for (var modifyAction : ActionType.values()) {
            MODIFY_PLACEHOLDERS[modifyAction.ordinal()] = new PlaceholderAction(
                    toTitle(modifyAction), null
            );
        }
    }

    private final JToolBar modifyActionsBar = createVerticalToolBar();

    private final Action[] modifyActionsArray = new Action[ActionType.values().length];

    private final Action importAction;
    private final Action exportAction;

    private final Action quitAction = new QuitAction();

    public enum ActionType {
        ADD, EDIT, DELETE;
    }

    private static String toTitle(
            Enum<?> enumValue
    ) {
        return enumValue.name().charAt(0)
                + enumValue.name().substring(1).toLowerCase();
    }

    public ToolBarManager(JComponent toolBarComponent, ImportAction iAction, ExportAction eAction) {
        toolBarComponent.setLayout(new BorderLayout());
        importAction = iAction;
        exportAction = eAction;

        toolBarComponent.add(modifyActionsBar, BorderLayout.NORTH);
        toolBarComponent.add(initGlobalActions(), BorderLayout.SOUTH);

        fillModifyPlaceholders();
        saveChanges();
    }

    private JToolBar initGlobalActions() {
        JToolBar globalActions = createVerticalToolBar();

        globalActions.add(importAction);
        globalActions.addSeparator(SEPARATOR);
        globalActions.add(exportAction);
        globalActions.addSeparator(QUIT_OFFSET);
        globalActions.add(quitAction);

        return globalActions;
    }

    private void fillModifyPlaceholders() {
        for (var modify : ActionType.values()) {
            modifyActionsArray[modify.ordinal()] = MODIFY_PLACEHOLDERS[modify.ordinal()];
        }
    }

    private JToolBar createVerticalToolBar() {
        JToolBar verticalTools = new JToolBar();
        verticalTools.setLayout(new BoxLayout(verticalTools, BoxLayout.Y_AXIS));
        verticalTools.setFloatable(false);

        return verticalTools;
    }

    public ToolBarManager reset() {
        modifyActionsBar.removeAll();
        fillModifyPlaceholders();
        return this;
    }

    public ToolBarManager addAction(ActionType typeOfAction, Action action) {
        modifyActionsArray[typeOfAction.ordinal()] = action;
        return this;
    }

    public void saveChanges() {
        modifyActionsBar.addSeparator(MODIFY_TOOLS_OFFSET);

        for (var action : modifyActionsArray) {
            modifyActionsBar.add(action);
            modifyActionsBar.addSeparator(SEPARATOR);
        }
    }
}
