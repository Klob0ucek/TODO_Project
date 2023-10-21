package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.Event;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddEvent extends AbstractAddAction {
    public AddEvent(
            TabHolder tabHolder
    ) {
        super(null, tabHolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currentTable = (JTable) tabHolder.getCurrentTab().getComponent();
        var currentTableModel = (ScheduleTableModel) currentTable.getModel();

        var categoryTable = (JTable) tabHolder.getTabAt(1).getComponent();
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddEventDialog(new Event(), new CategoryListModel(categories));
        dialog.show(currentTable, "Add event")
                .ifPresent(currentTableModel::addRow);
    }
}
