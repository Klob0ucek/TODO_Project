package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.action.PlaceholderAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.QuitAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.util.Arrays;


public class ToolBarManager {
    private final JToolBar modifyActions = createVerticalToolBar();
    private final JToolBar portActions = createVerticalToolBar();

    private final Action[] modifyActionsBuffer = new Action[ModifyAction.values().length];
    private final Action[] portActionsBuffer = new Action[PortAction.values().length];

    private final Action[] modifyPlaceholders = new Action[ModifyAction.values().length];
    private final Action[] portPlaceholders = new Action[PortAction.values().length];

    private final Action quitAction = new QuitAction();

    public enum ModifyAction {
        ADD, EDIT, DELETE;
    }

    public enum PortAction {
        IMPORT, EXPORT;
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
        toolBarComponent.add(portActions, BorderLayout.SOUTH);

        initPlaceholders();
        saveChanges();
    }

    private void initPlaceholders() {
        for (var modify : ModifyAction.values()) {
            modifyPlaceholders[modify.ordinal()] = new PlaceholderAction(toTitle(modify), null);
        }
        for (var port : PortAction.values()) {
            portPlaceholders[port.ordinal()] = new PlaceholderAction(toTitle(port), null);
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
        portActions.removeAll();

        Arrays.fill(modifyActionsBuffer, null);
        Arrays.fill(portActionsBuffer, null);

        return this;
    }

    public ToolBarManager addAction(
            ModifyAction typeOfAction,
            Action action
    ) {
        modifyActionsBuffer[typeOfAction.ordinal()] = action;
        return this;
    }

    public ToolBarManager addAction(
            PortAction typeOfAction,
            Action action
    ) {
        portActionsBuffer[typeOfAction.ordinal()] = action;
        return this;
    }

    public void saveChanges() {
        Action bufferedAction;

        for (int i = 0; i < modifyActionsBuffer.length; i++) {
            bufferedAction = modifyActionsBuffer[i];
            modifyActions.add(
                    bufferedAction == null ? modifyPlaceholders[i] : bufferedAction
            );
        }

        for (int i = 0; i < portActionsBuffer.length; i++) {
            bufferedAction = portActionsBuffer[i];
            portActions.add(
                    bufferedAction == null ? portPlaceholders[i] : bufferedAction
            );
        }
        portActions.add(quitAction);
    }
}
