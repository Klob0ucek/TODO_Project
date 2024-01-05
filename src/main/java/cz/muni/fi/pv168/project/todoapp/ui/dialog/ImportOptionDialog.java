package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class ImportOptionDialog extends EntityDialog<ImportOption> {
    private final ComboBoxModel<ImportOption> importOptionModel = new DefaultComboBoxModel<>(ImportOption.values());
    private final String importPath;

    public ImportOptionDialog(String importPath) {
        this.importPath = importPath;
        addFields();
    }

    private void addFields() {
        add("Import from file:", pathTextFieldSetup());
        add("Import option:", new JComboBox<>(importOptionModel));
    }

    private JTextField pathTextFieldSetup() {
        var path = new JTextField(importPath);
        path.setEditable(false);
        path.setFont(new Font("Tahoma", Font.BOLD, 11));
        return path;
    }

    @Override
    ImportOption getEntity() {
        return (ImportOption) importOptionModel.getSelectedItem();
    }
}
