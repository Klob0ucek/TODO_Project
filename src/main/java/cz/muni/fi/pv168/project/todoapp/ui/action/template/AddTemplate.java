package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddTemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabIndices;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddTemplate extends AbstractAddAction {
    public AddTemplate(
            TabHolder tabHolder,
            JTable table
    ) {
        super(null, tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) table.getModel();

        var categoryTable = (JTable) tabHolder.getTabAt(TabIndices.CATEGORIES.getIndex()).getComponent();
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddTemplateDialog(new CategoryListModel(categories));
        dialog.show(table, "Add template").ifPresent(templateTableModel::addRow);
    }
}
