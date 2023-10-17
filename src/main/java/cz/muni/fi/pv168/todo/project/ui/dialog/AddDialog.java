package cz.muni.fi.pv168.todo.project.ui.dialog;

import com.github.lgooddatepicker.components.DateTimePicker;
import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.Event;
import cz.muni.fi.pv168.todo.project.ui.model.ComboBoxModelAdapter;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import java.time.Duration;
import java.util.List;

public class AddDialog extends EntityDialog<Event> {
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();
    private final DateTimePicker dateTimePicker = new DateTimePicker();

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
        add("Date and time:", dateTimePicker);
    }

    @Override
    Event getEntity() {
        event.setDone(false);
        event.setName(nameField.getText());
        event.setCategories(List.of((Category) categoryModel.getSelectedItem()));
        event.setLocation(locationField.getText());
        event.setDate(dateTimePicker.getDatePicker().getDate());
        event.setTime(dateTimePicker.getTimePicker().getTime());
        event.setDuration(Duration.ofMinutes(30));
        return event;
    }
}
