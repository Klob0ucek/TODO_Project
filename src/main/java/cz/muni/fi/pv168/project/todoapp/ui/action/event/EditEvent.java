package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.EventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.ListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditEvent extends AbstractEditAction {
    public EditEvent(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.EDIT.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Edit selected event (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.checkSelectedCountAndCancelEditing();
        int modelRow = getTable().convertRowIndexToModel(super.getSelectedRowModelIndex());
        var event = ((ScheduleTableModel) getTable().getModel()).getEntity(modelRow);
        ListModel<Template> templateListModel = new ListModel<>(getCrudHolder().getTemplates());
        ListModel<Interval> intervalListModel = new ListModel<>(getCrudHolder().getIntervals());
        var dialog = new EventDialog(templateListModel, intervalListModel, getCrudHolder().getCategories(), event);
        dialog.show(getFrame(), "Edit Event").ifPresent(((ScheduleTableModel) getTable().getModel())::updateRow);
    }
}
