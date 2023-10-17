package cz.muni.fi.pv168.todo.project.ui.tab;

import cz.muni.fi.pv168.todo.project.ui.action.ActionType;
import cz.muni.fi.pv168.todo.project.ui.action.SmartAction;

import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
    private final List<SmartAction> actions;
    private final Set<ActionType> enabledActions;

    public static class Builder {
        private final String title;
        private final Component component;
        private final List<SmartAction> actions;
        private final Set<ActionType> enabledActions = new HashSet<>();
        private Icon icon = null;
        private String tip = null;

        public Builder(
                String title,
                Component component,
                List<SmartAction> actions
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
            List<SmartAction> actions,
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
            action.setEnabled(enabledActions.contains(action.getActionType()));
        }
    }

    public void addToPane(
            JTabbedPane tabbedPane
    ) {
        tabbedPane.addTab(title, icon, new JScrollPane(component), tip);
    }
}
