package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.TemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTable;

public class AddTemplate extends AbstractAddAction {
    public AddTemplate(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.ADD.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Add new template (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            var dialog = new TemplateDialog(getCrudHolder().getCategories());
            dialog.show(getFrame(), "Add template").ifPresent(((TemplateTableModel) getTable().getModel())::addRow);
        } catch (ValidationException validationException) {
            new NotificationDialog(getFrame(), "Invalid template not created!").showNotification();
            return;
        }
        new NotificationDialog(getFrame(), "Template added successfully!").showNotification();
    }
}
