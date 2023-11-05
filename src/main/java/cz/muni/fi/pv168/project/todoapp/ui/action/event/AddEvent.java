package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabIndices;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddEvent extends AbstractAddAction {
    public AddEvent(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var scheduleTableModel = (ScheduleTableModel) table.getModel();

        var categoryTable = (JTable) tabHolder.getTabAt(TabIndices.CATEGORIES.getIndex()).getComponent();
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddEventDialog(new CategoryListModel(categories));
        dialog.show(table, "Add event").ifPresent(scheduleTableModel::addRow);
    }
}
