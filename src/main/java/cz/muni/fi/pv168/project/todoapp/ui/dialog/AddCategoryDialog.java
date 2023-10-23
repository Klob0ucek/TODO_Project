package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddCategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel = new DefaultComboBoxModel<>(CategoryColor.values());

    public AddCategoryDialog() {
        addFields();
    }

    private void addFields() {
        add("Name:", nameField);
        add("Color:", new JComboBox<>(categoryColorModel));
    }

    @Override
    Category getEntity() {
        String name = nameField.getText();
        CategoryColor color = (CategoryColor) categoryColorModel.getSelectedItem();
        return new Category(name, color);
    }
}
