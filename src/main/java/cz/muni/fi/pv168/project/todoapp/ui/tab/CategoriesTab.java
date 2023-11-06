package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.AddCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.DeleteCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.EditCategory;

import javax.swing.Icon;
import javax.swing.JTable;

public class CategoriesTab extends GeneralTab {
    protected static class Builder extends GeneralTab.Builder<Builder> {
        @Override
        public Builder self() {
            return this;
        }
    }

    public CategoriesTab(
            String title,
            Icon icon,
            JTable table,
            String tip,
            ToolBarManager toolBarManager
    ) {
        super(
                new Builder()
                        .addTabDetails(title, icon, table, tip)
                        .addToolBarManager(toolBarManager)
                        .addAddAction(new AddCategory(table))
                        .addEditAction(new EditCategory(table))
                        .addDeleteAction(new DeleteCategory(table))
        );
    }
}
