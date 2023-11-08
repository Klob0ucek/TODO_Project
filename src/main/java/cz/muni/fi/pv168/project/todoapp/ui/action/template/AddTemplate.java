package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddTemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;

import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Supplier;

public class AddTemplate extends AbstractAddAction {
    private final Supplier<CategoryTableModel> categoryTableModelSupplier;

    public AddTemplate(
            JTable table,
            Supplier<CategoryTableModel> tableModelSupplier
    ) {
        super(null, table);
        this.categoryTableModelSupplier = tableModelSupplier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) this.getTable().getModel();

        var categoryTableModel = categoryTableModelSupplier.get();
        List<Category> categories = categoryTableModel.getCategories();

        var dialog = new AddTemplateDialog(new CategoryListModel(categories));
        dialog.show(this.getTable(), "Add template").ifPresent(templateTableModel::addRow);
    }
}
