package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel = new DefaultComboBoxModel<>(CategoryColor.values());

    private final Category category = new Category();

    public CategoryDialog() {
        addFields();
    }

    public CategoryDialog(Category category) {
        addFields();
        makeCopy(category);
        setFields(category);
    }

    private void makeCopy(Category category) {
        this.category.setGuid(category.getGuid());
        this.category.setName(category.getName());
        this.category.setColor(category.getColor());
    }

    private void setFields(Category category) {
        nameField.setText(category.getName());
        categoryColorModel.setSelectedItem(category.getColor());
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
