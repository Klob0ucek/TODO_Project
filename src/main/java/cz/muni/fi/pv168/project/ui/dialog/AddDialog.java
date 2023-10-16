package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.model.Category;
import cz.muni.fi.pv168.project.model.Event;
import cz.muni.fi.pv168.project.ui.model.ComboBoxModelAdapter;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddDialog extends EntityDialog<Event> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();

    private final Event event;

    public AddDialog(Event event, ListModel<Category> categoryModel) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.event = event;
        addFields();
    }

    private void addFields() {
        add("Name:", nameField);
        add("Category:", new JComboBox<>(categoryModel));
        add("Location:", locationField);
    }

    @Override
    Event getEntity() {
        event.setDone(false);
        event.setName(nameField.getText());
        event.setCategory(((Category) categoryModel.getSelectedItem()).getName());
        event.setLocation(locationField.getText());
        event.setDate(LocalDate.of(2023, 10, 10));
        event.setTime(LocalTime.of(10, 0));
        return event;
    }
}
