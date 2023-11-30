package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ExistingNameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.TemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditTemplate extends AbstractEditAction {
    public EditTemplate(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.EDIT.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Edit selected template (Alt + e)");
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

        var oldEntity = ((TemplateTableModel) getTable().getModel()).getEntity(super.getSelectedRowModelIndex());
        var dialog = new TemplateDialog(getCrudHolder().getCategories(), oldEntity);
        var newEntity = dialog.show(getFrame(), "Edit Template");

        while (newEntity.isPresent()) {
            try {
                ((TemplateTableModel) getTable().getModel()).updateRow(newEntity.get());
                new NotificationDialog(getFrame(), "Template edited successfully.").showNotification();
                return;
            } catch (ValidationException validationException) {
                new NotificationDialog(getFrame(), "Invalid template changes - data not saved!",
                        validationException.getValidationErrors()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Template");
            } catch (ExistingNameException nameException) {
                new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
                newEntity = dialog.show(getFrame(), "Edit Template");
            }
        }
    }
}
