package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.IntervalDialog;
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
        super(Icons.EDIT.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Edit selected interval (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.checkSelectedCountAndCancelEditing();
        int modelRow = getTable().convertRowIndexToModel(super.getSelectedRowModelIndex());
        var interval = ((IntervalTableModel) getTable().getModel()).getEntity(modelRow);
        var dialog = new IntervalDialog(interval);
        dialog.show(getFrame(), "Edit Interval").ifPresent(((IntervalTableModel) getTable().getModel())::updateRow);
    }
}
