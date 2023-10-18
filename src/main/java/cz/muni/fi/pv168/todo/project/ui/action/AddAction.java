package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.Event;
import cz.muni.fi.pv168.todo.project.ui.dialog.AddEventDialog;
import cz.muni.fi.pv168.todo.project.ui.model.BasicTableModel;
import cz.muni.fi.pv168.todo.project.ui.model.CategoryListModel;
import cz.muni.fi.pv168.todo.project.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.todo.project.ui.model.ScheduleTableModel;
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
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public TabHolder getTabHolder() {
        return tabHolder;
    }
}
