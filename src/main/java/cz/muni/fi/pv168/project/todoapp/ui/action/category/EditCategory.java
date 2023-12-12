package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.business.error.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;
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
    final Runnable refresher;

    public EditCategory(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder,
            Runnable refresher
    ) {
        super(table, frame, crudHolder);
        this.refresher = refresher;
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

        var oldEntity = ((CategoryTableModel) getTable().getModel()).getEntity(super.getSelectedRowModelIndex());
        var dialog = new CategoryDialog(oldEntity, getCrudHolder());
        var newEntity = dialog.show(getFrame(), "Edit Category");

        while (newEntity.isPresent()) {
            try {
                ((CategoryTableModel) getTable().getModel()).updateRow(newEntity.get());
                new NotificationDialog(getFrame(), "Category edited successfully.").showNotification();
                break;
            } catch (ValidationException validationException) {
                new NotificationDialog(getFrame(), "Invalid Category changes - data not saved!",
                        validationException.getValidationErrors()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Category");
            } catch (ExistingNameException nameException) {
                new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Category");
            }
        }
        // Category is edited, so we have to refresh event and template tables
        refresher.run();
    }
}
