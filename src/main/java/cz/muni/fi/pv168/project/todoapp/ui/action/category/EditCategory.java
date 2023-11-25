package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.business.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditCategory extends AbstractEditAction {
    public EditCategory(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.EDIT.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Edit selected category (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            super.checkSelectedCountAndCancelEditing();
        } catch (IllegalStateException illegalStateException) {
            new NotificationDialog(getFrame(), "Invalid number of selected rows!").showNotification();
            return;
        }

        var employee = ((CategoryTableModel) getTable().getModel()).getEntity(super.getSelectedRowModelIndex());
        var dialog = new CategoryDialog(employee);

        try {
            dialog.show(getFrame(), "Edit Category").ifPresent(((CategoryTableModel) getTable().getModel())::updateRow);
        } catch (ValidationException validationException) {
            new NotificationDialog(getFrame(), "Invalid Category changes - data not saved!").showNotification();
            return;
        }
        new NotificationDialog(getFrame(), "Category edited successfully.").showNotification();
    }
}
