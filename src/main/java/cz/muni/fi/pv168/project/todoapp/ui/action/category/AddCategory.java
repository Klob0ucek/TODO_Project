package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddCategoryDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class AddCategory extends AbstractAddAction {
    public AddCategory(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryTableModel) this.getTable().getModel();
        var dialog = new AddCategoryDialog();
        dialog.show(this.getTable(), "Add category").ifPresent(categoryTableModel::addRow);
    }
}
