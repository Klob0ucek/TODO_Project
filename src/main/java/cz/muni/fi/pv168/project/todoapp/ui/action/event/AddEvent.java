package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.EventRenameException;
import cz.muni.fi.pv168.project.todoapp.business.service.exeptions.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.EventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.model.ListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.JTable;

public class AddEvent extends AbstractAddAction {
    private final Filter filter;

    public AddEvent(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder,
            Filter filter
    ) {
        super(Icons.ADD.getIcon(), table, frame, crudHolder);
        this.filter = filter;
        putValue(SHORT_DESCRIPTION, "Add new category (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ListModel<Template> templateListModel = new ListModel<>(getCrudHolder().getTemplates());
        ListModel<Interval> intervalListModel = new ListModel<>(getCrudHolder().getIntervals());
        var dialog = new EventDialog(templateListModel, intervalListModel, getCrudHolder().getCategories());
        Optional<Event> event = dialog.show(getFrame(), "Add event");
        if (event.isPresent()) {
            try {
                filter.updateIntervals((int) event.get().getDuration().toMinutes());
                ((ScheduleTableModel) getTable().getModel()).addRow(event.get());
            } catch (ValidationException validationException) {
                new NotificationDialog(getFrame(), "Invalid event not created!",
                        validationException.getValidationErrors()).showNotification();
                return;
            } catch (EventRenameException nameException) {
                new NotificationDialog(getFrame(), nameException.getUserMessage()).showNotification();
                return;
            }
            new NotificationDialog(getFrame(), "Event added successfully.").showNotification();
        }
    }
}
