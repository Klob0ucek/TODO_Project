package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.Event;
import cz.muni.fi.pv168.todo.project.model.Interval;
import cz.muni.fi.pv168.todo.project.ui.dialog.AddCategoryDialog;
import cz.muni.fi.pv168.todo.project.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.todo.project.ui.dialog.AddIntervalDialog;
import cz.muni.fi.pv168.todo.project.ui.model.*;
import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.util.List;

public class AddAction extends AbstractAction {
    private final ActionType actionType = ActionType.ADD;
    private final TabHolder tabHolder;

    public AddAction(TabHolder tabHolder) {
        super("Add", null); // TODO: add *icon*
        this.tabHolder = tabHolder;
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        var currentTable = (JTable) tabHolder.getCurrentTab().getComponent();
        var currentTableModel = (BasicTableModel) currentTable.getModel();

        var categoryTable = (JTable) tabHolder.getTabAt(1).getComponent();
        CategoryTableModel categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        if (currentTableModel instanceof ScheduleTableModel) {
            var dialog = new AddEventDialog(new Event(), new CategoryListModel(categories));
            dialog.show(currentTable, "Add event")
                    .ifPresent(currentTableModel::addRow);
        } else if (currentTableModel instanceof CategoryTableModel) {
            var dialog = new AddCategoryDialog(new Category());
            dialog.show(currentTable, "Add category")
                    .ifPresent(currentTableModel::addRow);
        } else if (currentTableModel instanceof TemplateTableModel) {
            // TODO
        } else if (currentTableModel instanceof IntervalTableModel) {
            var dialog = new AddIntervalDialog(new Interval());
            dialog.show(currentTable, "Add interval")
                    .ifPresent(currentTableModel::addRow);
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public TabHolder getTabHolder() {
        return tabHolder;
    }
}
