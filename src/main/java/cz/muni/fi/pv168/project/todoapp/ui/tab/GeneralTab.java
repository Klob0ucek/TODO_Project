package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Component;

public abstract class GeneralTab {
    private final String title;
    private final String tip;
    private final Icon icon;
    private final Component component;

    private final ToolBarManager toolBarManager;

    private final Action addAction;
    private final Action editAction;
    private final Action deleteAction;

    protected abstract static class Builder<T extends Builder<T>> {
        private String title;
        private String tip;
        private Icon icon;
        private Component component;

        private ToolBarManager toolBarManager;

        private Action addAction;
        private Action editAction;
        private Action deleteAction;

        protected abstract T self();

        public T addTabDetails(
                String title,
                Icon icon,
                Component component,
                String tip
        ) {
            this.title = title;
            this.tip = tip;
            this.icon = icon;
            this.component = component;
            return self();
        }

        public T addToolBarManager(
                ToolBarManager toolBarManager
        ) {
            this.toolBarManager = toolBarManager;
            return self();
        }

        public T addAddAction(
                Action addAction
        ) {
            this.addAction = addAction;
            return self();
        }

        public T addEditAction(
                Action editAction
        ) {
            this.editAction = editAction;
            return self();
        }

        public T addDeleteAction(
                Action deleteAction
        ) {
            this.deleteAction = deleteAction;
            return self();
        }
    }

    protected GeneralTab(
            Builder<?> builder
    ) {
        this.title = builder.title;
        this.tip = builder.tip;
        this.icon = builder.icon;
        this.component = builder.component;

        this.addAction = builder.addAction;
        this.editAction = builder.editAction;
        this.deleteAction = builder.deleteAction;

        this.toolBarManager = builder.toolBarManager;
    }

    public Component getComponent() {
        return component;
    }

    public ToolBarManager getToolBarManager() {
        return toolBarManager;
    }

    public void updateToolBar() {
        this.toolBarManager
                .reset()
                .addAction(ToolBarManager.ActionType.ADD, this.addAction)
                .addAction(ToolBarManager.ActionType.EDIT, this.editAction)
                .addAction(ToolBarManager.ActionType.DELETE, this.deleteAction)
                .saveChanges();
    }

    public void addToPane(
            JTabbedPane tabbedPane
    ) {
        tabbedPane.addTab(title, icon, new JScrollPane(component), tip);
    }
}
