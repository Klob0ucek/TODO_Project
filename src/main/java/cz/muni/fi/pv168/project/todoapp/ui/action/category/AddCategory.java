package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddCategoryDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddCategory extends AbstractAddAction {
    public AddCategory(
            TabHolder tabHolder
    ) {
        super(null, tabHolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currentTable = (JTable) tabHolder.getCurrentTab().getComponent();
        var currentTableModel = (CategoryTableModel) currentTable.getModel();

        var dialog = new AddCategoryDialog(new Category());
        dialog.show(currentTable, "Add category")
                .ifPresent(currentTableModel::addRow);
    }
}
