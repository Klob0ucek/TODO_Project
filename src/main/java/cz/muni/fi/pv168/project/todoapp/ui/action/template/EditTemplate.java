package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
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
        super.checkSelectedCountAndCancelEditing();
        int modelRow = getTable().convertRowIndexToModel(super.getSelectedRowModelIndex());
        var template = ((TemplateTableModel) getTable().getModel()).getEntity(modelRow);
        var dialog = new TemplateDialog(getCrudHolder().getCategories(), template);
        dialog.show(getFrame(), "Edit Template").ifPresent(((TemplateTableModel) getTable().getModel())::updateRow);
    }
}
