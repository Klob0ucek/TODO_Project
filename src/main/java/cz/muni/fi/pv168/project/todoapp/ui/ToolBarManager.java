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
import java.util.Arrays;


public class ToolBarManager {
    private final static Dimension MODIFY_TOOLS_OFFSET;
    private final static Dimension QUIT_OFFSET;
    private final static Dimension SEPARATOR;

    static {
        MODIFY_TOOLS_OFFSET = new Dimension(0, 35);
        QUIT_OFFSET = new Dimension(0, 10);
        SEPARATOR = new Dimension(0, 3);
    }

    private final JToolBar modifyActions = createVerticalToolBar();

    private final Action[] modifyActionsBuffer = new Action[ModifyAction.values().length];
    private final Action[] modifyPlaceholders = new Action[ModifyAction.values().length];

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

        toolBarComponent.add(modifyActions, BorderLayout.NORTH);
        toolBarComponent.add(initGlobalActions(), BorderLayout.SOUTH);

        initModifyPlaceholders();
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

    private void initModifyPlaceholders() {
        for (var modify : ModifyAction.values()) {
            modifyPlaceholders[modify.ordinal()] = new PlaceholderAction(toTitle(modify), null);
        }
    }

    private JToolBar createVerticalToolBar() {
        JToolBar verticalTools = new JToolBar();
        verticalTools.setLayout(new BoxLayout(verticalTools, BoxLayout.Y_AXIS));
        verticalTools.setFloatable(false);

        return verticalTools;
    }

    public ToolBarManager reset() {
        modifyActions.removeAll();
        Arrays.fill(modifyActionsBuffer, null);
        return this;
    }

    public ToolBarManager addAction(
            ModifyAction typeOfAction,
            Action action
    ) {
        modifyActionsBuffer[typeOfAction.ordinal()] = action;
        return this;
    }

    public void saveChanges() {
        Action bufferedAction;

        modifyActions.addSeparator(MODIFY_TOOLS_OFFSET);

        for (int i = 0; i < modifyActionsBuffer.length; i++) {
            bufferedAction = modifyActionsBuffer[i];
            modifyActions.add(
                    bufferedAction == null ? modifyPlaceholders[i] : bufferedAction
            );
            modifyActions.addSeparator(SEPARATOR);
        }
    }
}
