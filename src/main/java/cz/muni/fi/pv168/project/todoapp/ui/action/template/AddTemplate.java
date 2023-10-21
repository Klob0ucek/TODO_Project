package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.Template;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddTemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddTemplate extends AbstractAddAction {
    public AddTemplate(
            TabHolder tabHolder
    ) {
        super(null, tabHolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currentTable = (JTable) tabHolder.getCurrentTab().getComponent();
        var currentTableModel = (TemplateTableModel) currentTable.getModel();

        var categoryTable = (JTable) tabHolder.getTabAt(1).getComponent();
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddTemplateDialog(new Template(), new CategoryListModel(categories));
        dialog.show(currentTable, "Add template")
                .ifPresent(currentTableModel::addRow);
    }
}
