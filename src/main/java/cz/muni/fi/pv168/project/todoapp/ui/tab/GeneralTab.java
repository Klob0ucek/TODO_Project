package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.utils.Buildable;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Component;

public abstract class GeneralTab {
    private final String title;
    private final Component component;
    private final ToolBarManager toolBarManager;
    private final Action addAction;
    private final Action editAction;
    private final Action deleteAction;
    private final Runnable refresher;

    public abstract static class BuildTemplate<T extends BuildTemplate<T>> implements Buildable<GeneralTab> {
        private String title;
        private JFrame frame;
        private Component component;
        private ToolBarManager toolBarManager;
        private Action addAction;
        private Action editAction;
        private Action deleteAction;
        private CrudHolder crudHolder;
        private Filter filter;
        private Runnable refresher;

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

        public Filter getFilter() {
            return filter;
        }

        public Runnable getRefresher() {
            return refresher;
        }

        public T addTabDetails(
                String title,
                Component component,
                JFrame frame,
                CrudHolder crudHolder
        ) {
            this.title = title;
            this.component = component;
            this.frame = frame;
            this.crudHolder = crudHolder;
            return self();
        }

        public T addFilter(Filter filter) {
            this.filter = filter;
            return self();
        }

        public T addToolBarManager(
                ToolBarManager toolBarManager
        ) {
            this.toolBarManager = toolBarManager;
            return self();
        }

        public T addRefreshCallback(
                Runnable callback
        ) {
            this.refresher = callback;
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

        protected T addPopupMenu(JTable table) {
            table.setComponentPopupMenu(createTablePopupMenu());
            return self();
        }

        private JPopupMenu createTablePopupMenu() {
            var menu = new JPopupMenu();
            menu.add(addAction);
            menu.add(editAction);
            menu.add(deleteAction);
            return menu;
        }
    }

    protected GeneralTab(
            BuildTemplate<?> buildTemplate
    ) {
        this.title = buildTemplate.title;
        this.component = buildTemplate.component;

        this.addAction = buildTemplate.addAction;
        this.editAction = buildTemplate.editAction;
        this.deleteAction = buildTemplate.deleteAction;

        this.toolBarManager = buildTemplate.toolBarManager;
        this.refresher = buildTemplate.refresher;

        addActionManager();
    }

    protected void addActionManager() {
        var table = (JTable) this.getComponent();
        table.getSelectionModel().addListSelectionListener(
                (e) -> manageActions(table.getSelectedRowCount())
        );

        editAction.setEnabled(false);
        deleteAction.setEnabled(false);
    }

    private void manageActions(
            int selectedRows
    ) {
        editAction.setEnabled(selectedRows == 1);
        deleteAction.setEnabled(selectedRows >= 1);
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
