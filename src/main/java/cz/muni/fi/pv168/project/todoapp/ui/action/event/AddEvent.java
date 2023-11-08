package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;

import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Supplier;

public class AddEvent extends AbstractAddAction {
    private final Supplier<CategoryTableModel> categoryTableModelSupplier;

    public AddEvent(
            JTable table,
            Supplier<CategoryTableModel> tableModelSupplier
    ) {
        super(null, table);
        this.categoryTableModelSupplier = tableModelSupplier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var scheduleTableModel = (ScheduleTableModel) this.getTable().getModel();

        var categoryTableModel = categoryTableModelSupplier.get();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddEventDialog(new CategoryListModel(categories));
        dialog.show(this.getTable(), "Add event").ifPresent(scheduleTableModel::addRow);
    }
}
