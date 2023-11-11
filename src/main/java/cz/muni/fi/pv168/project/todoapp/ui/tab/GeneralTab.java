package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.utils.Buildable;

import javax.swing.*;
import java.awt.*;

public abstract class GeneralTab {
    protected String title;
    private final Component component;
    private final ToolBarManager toolBarManager;
    private final Action addAction;
    private final Action editAction;
    private final Action deleteAction;

    public abstract static class BuildTemplate<T extends BuildTemplate<T>> implements Buildable<GeneralTab> {
        private JFrame frame;
        private Component component;
        private ToolBarManager toolBarManager;
        private Action addAction;
        private Action editAction;
        private Action deleteAction;
        private CrudHolder crudHolder;

        protected abstract T self();

        protected Component getComponent() {
            return this.component;
        }

        protected JFrame getFrame() {
            return this.frame;
        }

        protected CrudHolder getCrudHolder() {
            return crudHolder;
        }

        public T addTabDetails(
                Component component,
                JFrame frame,
                CrudHolder crudHolder
        ) {
            this.component = component;
            this.frame = frame;
            this.crudHolder = crudHolder;
            return self();
        }

        public T addToolBarManager(
                ToolBarManager toolBarManager
        ) {
            this.toolBarManager = toolBarManager;
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
        tabbedPane.addTab(title, null, new JScrollPane(component), null);
    }

}
