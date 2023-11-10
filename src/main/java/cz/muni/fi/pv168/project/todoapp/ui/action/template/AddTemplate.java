package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddTemplateDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryListModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Supplier;

public class AddTemplate extends AbstractAddAction {
    private final Supplier<List<Category>> categoriesSupplier;

    public AddTemplate(
            JTable table,
            Supplier<List<Category>> categoriesSupplier,
            JFrame frame
    ) {
        super(Icons.ADD.getIcon(), table, frame);
        this.categoriesSupplier = categoriesSupplier;
        putValue(SHORT_DESCRIPTION, "Add new template (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) this.getTable().getModel();
        
        var dialog = new AddTemplateDialog(new CategoryListModel(categoriesSupplier.get()));
        dialog.show(this.getTable(), "Add template").ifPresent(templateTableModel::addRow);
    }
}
