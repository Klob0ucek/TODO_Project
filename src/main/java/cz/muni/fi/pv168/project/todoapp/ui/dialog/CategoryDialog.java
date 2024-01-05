package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ComboBoxRenderer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CategoryDialog extends EntityDialog<Category> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<CategoryColor> categoryColorModel;

    private String guid;

    public CategoryDialog(List<Category> categories) {
        this.categoryColorModel = new DefaultComboBoxModel<>(getAvailableColors(categories));
        addFields();
        guid = UniqueIdProvider.newId();
    }

    public CategoryDialog(Category category, List<Category> categories) {
        this.categoryColorModel = new DefaultComboBoxModel<>(getAvailableColors(categories));
        addFields();
        guid = category.getGuid();
        setFields(category);
    }

    public Collection<CategoryColor> getUsedColors(List<Category> categories) {
        return categories.stream().map(Category::getColor).toList();
    }

    public CategoryColor[] getAvailableColors(List<Category> categories) {
        return Arrays.stream(CategoryColor.values()).filter(categoryColor -> !getUsedColors(categories).contains(categoryColor)).toArray(CategoryColor[]::new);
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
        return new Category(guid, nameField.getText(), (CategoryColor) categoryColorModel.getSelectedItem());
    }
}
