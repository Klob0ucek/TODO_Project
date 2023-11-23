package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.EventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.ListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddEvent extends AbstractAddAction {
    public AddEvent(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(Icons.ADD.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Add new category (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListModel<Template> templateListModel = new ListModel<>(getCrudHolder().getTemplates());
        ListModel<Interval> intervalListModel = new ListModel<>(getCrudHolder().getIntervals());
        var dialog = new EventDialog(templateListModel, intervalListModel, getCrudHolder().getCategories());
        dialog.show(getFrame(), "Add event").ifPresent(((ScheduleTableModel) getTable().getModel())::addRow);
        new NotificationDialog(getFrame(), "Event added successfully!").showNotification();
    }
}
