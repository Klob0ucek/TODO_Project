package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Supplier;

public class AddEvent extends AbstractAddAction {
    private final Supplier<List<Category>> categoriesSupplier;

    public AddEvent(
            JTable table,
            Supplier<List<Category>> tableModelSupplier,
            JFrame frame
    ) {
        super(Icons.ADD.getIcon(), table, frame);
        this.categoriesSupplier = tableModelSupplier;
        putValue(SHORT_DESCRIPTION, "Add new category (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var scheduleTableModel = (ScheduleTableModel) this.getTable().getModel();

        var dialog = new AddEventDialog(new CategoryListModel(categoriesSupplier.get()));
        dialog.show(this.getTable(), "Add event").ifPresent(scheduleTableModel::addRow);

        NotificationDialog notificationDialog = new NotificationDialog(super.getFrame(), "Event added successfully!");
        notificationDialog.showNotification();
    }
}
