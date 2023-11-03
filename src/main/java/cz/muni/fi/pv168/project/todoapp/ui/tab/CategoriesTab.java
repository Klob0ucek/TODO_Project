package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.AddCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.DeleteCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.category.EditCategory;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JTable;

import java.awt.Component;

public class CategoriesTab extends GeneralTab {
    private final Action add = new AddCategory(tabHolder, (JTable) component);
    private final Action edit = new EditCategory(tabHolder, (JTable) component);
    private final Action delete = new DeleteCategory(tabHolder, (JTable) component);

    public CategoriesTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarHolder,
            TabHolder tabHolder
    ) {
        super(title, icon, component, tip, toolBarHolder, tabHolder);
    }

    @Override
    public void updateToolBar() {
        this.getToolBarManager()
                .reset()
                .addAction(ToolBarManager.ModifyAction.ADD, add)
                .addAction(ToolBarManager.ModifyAction.EDIT, edit)
                .addAction(ToolBarManager.ModifyAction.DELETE, delete)
                .saveChanges();
    }
}
