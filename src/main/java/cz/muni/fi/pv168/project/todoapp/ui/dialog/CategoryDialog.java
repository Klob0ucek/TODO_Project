package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ComboBoxRenderer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel;

    private final Category category = new Category();

    public CategoryDialog(List<Category> categories) {
        this.categoryColorModel = new DefaultComboBoxModel<>(getAvailableColors(categories));
        addFields();
    }

    public CategoryDialog(Category category, List<Category> categories) {
        this.categoryColorModel = new DefaultComboBoxModel<>(getAvailableColors(categories));
        addFields();
        makeCopy(category);
        setFields(category);
    }

    public Collection<CategoryColor> getUsedColors(List<Category> categories) {
        return categories.stream().map(Category::getColor).toList();
    }

    public CategoryColor[] getAvailableColors(List<Category> categories) {
        return Arrays.stream(CategoryColor.values()).filter(categoryColor -> !getUsedColors(categories).contains(categoryColor)).toArray(CategoryColor[]::new);
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
        var comboBox = new JComboBox<>(categoryColorModel);
        comboBox.setRenderer(new ComboBoxRenderer());
        add("Color:", comboBox);
    }

    @Override
    Category getEntity() {
        category.setName(nameField.getText());
        category.setColor((CategoryColor) categoryColorModel.getSelectedItem());
        return category;
    }
}
