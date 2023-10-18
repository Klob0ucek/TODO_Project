package cz.muni.fi.pv168.todo.project.ui.dialog;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.CategoryColor;

import javax.swing.*;

public class AddCategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel = new DefaultComboBoxModel<>(CategoryColor.values());

    private final Category category;

    public AddCategoryDialog(Category category) {
        this.category = category;
        addFields();
    }

    private void addFields() {
        add("Name:", nameField);
        add("Color:", new JComboBox<>(categoryColorModel));
    }

    @Override
    Category getEntity() {
        category.setName(nameField.getText());
        category.setColor((CategoryColor) categoryColorModel.getSelectedItem());
        return category;
    }
}
