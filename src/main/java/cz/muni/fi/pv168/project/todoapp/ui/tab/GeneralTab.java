package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.utils.Buildable;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.util.function.Supplier;

public abstract class GeneralTab {
    private final String title;
    private final String tip;
    private final Icon icon;
    private final Component component;

    private final ToolBarManager toolBarManager;

    private final Action addAction;
    private final Action editAction;
    private final Action deleteAction;

    public abstract static class BuildTemplate<T extends BuildTemplate<T>> implements Buildable<GeneralTab> {
        private String title;
        private String tip;
        private Icon icon;
        private Component component;

        private ToolBarManager toolBarManager;

        private Supplier<CategoryTableModel> categoryTableModelSupplier;

        private Action addAction;
        private Action editAction;
        private Action deleteAction;

        protected abstract T self();

        protected Component getComponent() {
            return this.component;
        }

        protected Supplier<CategoryTableModel> getCategoryTableModelSupplier() {
            return this.categoryTableModelSupplier;
        }

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

        public T addCategoryTableSupplier(
                Supplier<CategoryTableModel> tableModelSupplier
        ) {
            this.categoryTableModelSupplier = tableModelSupplier;
            return self();
        }

        protected T addAddAction(
                Action addAction
        ) {
            this.addAction = addAction;
            return self();
        }

        protected T addEditAction(
                Action editAction
        ) {
            this.editAction = editAction;
            return self();
        }

        protected T addDeleteAction(
                Action deleteAction
        ) {
            this.deleteAction = deleteAction;
            return self();
        }
    }

    protected GeneralTab(
            BuildTemplate<?> buildTemplate
    ) {
        this.title = buildTemplate.title;
        this.tip = buildTemplate.tip;
        this.icon = buildTemplate.icon;
        this.component = buildTemplate.component;

        this.addAction = buildTemplate.addAction;
        this.editAction = buildTemplate.editAction;
        this.deleteAction = buildTemplate.deleteAction;

        this.toolBarManager = buildTemplate.toolBarManager;
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
