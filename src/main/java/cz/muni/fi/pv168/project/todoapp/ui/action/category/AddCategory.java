package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddCategory extends AbstractAddAction {
    public AddCategory(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.ADD.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Add new category (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A); // Alt + A
        // putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N")); Doesnt work for me
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            var dialog = new CategoryDialog();
            var newEntity = dialog.show(getFrame(), "Add category");
            if (newEntity.isPresent()) {
                ((CategoryTableModel) getTable().getModel()).addRow(newEntity.get());
                new NotificationDialog(getFrame(), "Category added successfully.").showNotification();
            }
        } catch (ValidationException validationException) {
            new NotificationDialog(getFrame(), "Invalid category not created!",
                    validationException.getValidationErrors()).showNotification();
        } catch (ExistingNameException nameException) {
            new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
        }
    }
}
