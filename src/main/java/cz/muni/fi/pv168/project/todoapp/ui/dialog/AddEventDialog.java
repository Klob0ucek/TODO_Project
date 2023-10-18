package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.DateTimePicker;
import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.Event;
import cz.muni.fi.pv168.project.todoapp.ui.model.ComboBoxModelAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.time.Duration;
import java.util.List;

public class AddEventDialog extends EntityDialog<Event> {
    private final JPanel topPanel = new JPanel();
    private final JCheckBox doneField = new JCheckBox();
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();
    private final DateTimePicker dateTimePicker = new DateTimePicker();
    private final JSpinner durationSpinner = new JSpinner();

    private final Event event;

    public AddEventDialog(Event event, ListModel<Category> categoryModel) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.event = event;
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        addFields();
    }

    private void addFields() {
        topPanel.add(new JLabel(" Done? "));
        topPanel.add(doneField);
        topPanel.add(new JLabel(" Name: "));
        topPanel.add(nameField);
        panel.add(topPanel);

        add("Category:", new JComboBox<>(categoryModel));
        add("Location:", locationField);
        add("Date and time:", dateTimePicker);
        add("Duration in minutes:", durationSpinner);
    }

    @Override
    Event getEntity() {
        event.setDone(doneField.isSelected());
        event.setName(nameField.getText());
        event.setCategories(List.of((Category) categoryModel.getSelectedItem()));
        event.setLocation(locationField.getText());
        event.setDate(dateTimePicker.getDatePicker().getDate());
        event.setTime(dateTimePicker.getTimePicker().getTime());
        event.setDuration(Duration.ofMinutes((Integer) durationSpinner.getValue()));
        return event;
    }
}
