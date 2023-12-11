package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.business.error.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.IntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditInterval extends AbstractEditAction {
    public EditInterval(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Edit selected interval (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            super.checkSelectedCountAndCancelEditing();
        } catch (IllegalStateException illegalStateException) {
            new NotificationDialog(getFrame(), "Invalid number of selected rows").showNotification();
            return;
        }

        var oldEntity = ((IntervalTableModel) getTable().getModel()).getEntity(super.getSelectedRowModelIndex());
        var dialog = new IntervalDialog(oldEntity);
        var newEntity = dialog.show(getFrame(), "Edit Interval");

        while (newEntity.isPresent()) {
            try {
                ((IntervalTableModel) getTable().getModel()).updateRow(newEntity.get());
                new NotificationDialog(getFrame(), "Interval edited successfully.").showNotification();
                return;
            } catch (ValidationException validationException) {
                new NotificationDialog(getFrame(), "Invalid interval changes - data not saved!",
                        validationException.getValidationErrors()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Interval");
            } catch (ExistingNameException nameException) {
                new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Interval");
            }
        }
    }
}
