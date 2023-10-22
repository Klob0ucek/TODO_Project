package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.action.PlaceholderAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.QuitAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ImportAction;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Dimension;


public class ToolBarManager {
    private final static Dimension MODIFY_TOOLS_OFFSET;
    private final static Dimension QUIT_OFFSET;
    private final static Dimension SEPARATOR;

    private final static Action[] MODIFY_PLACEHOLDERS = new Action[ModifyAction.values().length];

    static {
        MODIFY_TOOLS_OFFSET = new Dimension(0, 35);
        QUIT_OFFSET = new Dimension(0, 10);
        SEPARATOR = new Dimension(0, 3);

        for (var modify : ModifyAction.values()) {
            MODIFY_PLACEHOLDERS[modify.ordinal()] = new PlaceholderAction(toTitle(modify), null);
        }
    }

    private final JToolBar modifyActionsBar = createVerticalToolBar();

    private final Action[] modifyActionsArr = new Action[ModifyAction.values().length];

    private final Action importAction = new ImportAction();
    private final Action exportAction = new ExportAction();
    private final Action quitAction = new QuitAction();

    public enum ModifyAction {
        ADD, EDIT, DELETE;
    }

    private static String toTitle(
            Enum enumValue
    ) {
        return enumValue.name().charAt(0)
                + enumValue.name().substring(1).toLowerCase();
    }

    public ToolBarManager(
            JComponent toolBarComponent
    ) {
        toolBarComponent.setLayout(new BorderLayout());

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
        for (var modify : ModifyAction.values()) {
            modifyActionsArr[modify.ordinal()] = MODIFY_PLACEHOLDERS[modify.ordinal()];
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

    public ToolBarManager addAction(
            ModifyAction typeOfAction,
            Action action
    ) {
        modifyActionsArr[typeOfAction.ordinal()] = action;
        return this;
    }

    public void saveChanges() {
        modifyActionsBar.addSeparator(MODIFY_TOOLS_OFFSET);

        for (var a : modifyActionsArr) {
            modifyActionsBar.add(a);
            modifyActionsBar.addSeparator(SEPARATOR);
        }
    }
}
