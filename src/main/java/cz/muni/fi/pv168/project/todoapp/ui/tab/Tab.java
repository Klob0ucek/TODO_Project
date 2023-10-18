package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.action.*;
import cz.muni.fi.pv168.project.todoapp.ui.action.ActionType;

import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.Action;

import java.awt.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tab {
    private final String title;
    private final String tip;
    private final Icon icon;
    private final Component component;
    private final List<Action> actions;
    private final Set<ActionType> enabledActions;

    public static class Builder {
        private final String title;
        private final Component component;
        private final List<Action> actions;
        private final Set<ActionType> enabledActions = new HashSet<>();
        private Icon icon = null;
        private String tip = null;

        public Builder(
                String title,
                Component component,
                List<Action> actions
        ) {
            this.title = title;
            this.component = component;
            this.actions = actions;
        }

        public Builder addIcon(
                Icon icon
        ) {
            this.icon = icon;
            return this;
        }

        public Builder addTip(
                String tip
        ) {
            this.tip = tip;
            return this;
        }

        public Builder enableActions(
                ActionType... toEnable
        ) {
            Collections.addAll(enabledActions, toEnable);
            return this;
        }

        public Tab build() {
            return new Tab(title, icon, component, tip, actions, enabledActions);
        }
    }

    private Tab(
            String title,
            Icon icon,
            Component component,
            String tip,
            List<Action> actions,
            Set<ActionType> enabledActions
    ) {
        this.title = title;
        this.tip = tip;
        this.icon = icon;
        this.component = component;
        this.actions = actions;
        this.enabledActions = enabledActions;
    }

    public void updateActions() {
        for (var action : actions) {
            if (action instanceof AddAction) {
                action.setEnabled(enabledActions.contains(ActionType.ADD));
            } else if (action instanceof DeleteAction) {
                action.setEnabled(enabledActions.contains(ActionType.DELETE));
            } else if (action instanceof EditAction) {
                action.setEnabled(enabledActions.contains(ActionType.EDIT));
            } else if (action instanceof FilterAction) {
                action.setEnabled(enabledActions.contains(ActionType.FILTER));
            } else if (action instanceof ImportAction) {
                action.setEnabled(enabledActions.contains(ActionType.IMPORT));
            } else if (action instanceof ExportAction) {
                action.setEnabled(enabledActions.contains(ActionType.EXPORT));
            }
        }
    }

    public void addToPane(
            JTabbedPane tabbedPane
    ) {
        tabbedPane.addTab(title, icon, new JScrollPane(component), tip);
    }

    public Component getComponent() {
        return component;
    }
}
