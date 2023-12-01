package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.IntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddInterval extends AbstractAddAction {
    public AddInterval(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.ADD.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Add new interval (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new IntervalDialog();
        var newEntity = dialog.show(getFrame(), "Add interval");

        while (newEntity.isPresent()) {
            try {
                ((IntervalTableModel) getTable().getModel()).addRow(newEntity.get());
                new NotificationDialog(getFrame(), "Interval added successfully.").showNotification();
                return;
            } catch (ValidationException validationException) {
                new NotificationDialog(getFrame(), "Invalid interval not created!",
                        validationException.getValidationErrors()).showNotification();
                newEntity = dialog.show(getFrame(), "Add interval");
            } catch (ExistingNameException nameException) {
                new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
                newEntity = dialog.show(getFrame(), "Add interval");
            }
        }
    }
}
