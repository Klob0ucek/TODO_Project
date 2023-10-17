package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.Event;
import cz.muni.fi.pv168.todo.project.ui.dialog.AddDialog;
import cz.muni.fi.pv168.todo.project.ui.model.CategoryListModel;
import cz.muni.fi.pv168.todo.project.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.todo.project.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddAction extends SmartAction {
    public AddAction(
            TabHolder tabHolder
    ) {
        super("Add", null, ActionType.ADD, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        var table = (JTable) getTabHolder().getCurrentTab().getComponent();
        ScheduleTableModel tableModel = (ScheduleTableModel) table.getModel();

        var categoryTable = (JTable) getTabHolder().getTabAt(1).getComponent();
        CategoryTableModel categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddDialog(new Event(), new CategoryListModel(categories));
        dialog.show(table, "Add event")
                .ifPresent(tableModel::addRow);
    }
}
