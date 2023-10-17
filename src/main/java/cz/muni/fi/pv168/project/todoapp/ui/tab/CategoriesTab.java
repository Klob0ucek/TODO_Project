package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.categories.AddCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.categories.DeleteCategory;
import cz.muni.fi.pv168.project.todoapp.ui.action.categories.EditCategory;

import javax.swing.Action;
import javax.swing.Icon;

import java.awt.Component;

public class CategoriesTab extends GeneralTab {
    private final Action add = new AddCategory();
    private final Action edit = new EditCategory();
    private final Action delete = new DeleteCategory();

    public CategoriesTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarHolder
    ) {
        super(title, icon, component, tip, toolBarHolder);
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
