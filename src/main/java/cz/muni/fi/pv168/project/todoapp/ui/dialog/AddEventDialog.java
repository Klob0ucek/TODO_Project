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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AddEventDialog extends EntityDialog<Event> {
    private final JPanel topPanel = new JPanel();
    private final JCheckBox doneField = new JCheckBox();
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();
    private final DateTimePicker dateTimePicker = new DateTimePicker();
    private final JSpinner durationSpinner = new JSpinner();

    public AddEventDialog(ListModel<Category> categoryModel) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        topPanelSetup();
        addFields();
    }

    private void addFields() {
        panel.add(topPanel);
        add("Category:", new JComboBox<>(categoryModel));
        add("Location:", locationField);
        add("Date and time:", dateTimePicker);
        add("Duration in minutes:", durationSpinner);
    }

    private void topPanelSetup() {
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new JLabel(" Done? "));
        topPanel.add(doneField);
        topPanel.add(new JLabel(" Name: "));
        topPanel.add(nameField);
    }

    @Override
    Event getEntity() {
        boolean isDone = doneField.isSelected();
        String name = nameField.getText();
        List<Category> categories = List.of((Category) categoryModel.getSelectedItem());
        String location = locationField.getText();
        LocalDate date = dateTimePicker.getDatePicker().getDate();
        LocalTime time = dateTimePicker.getTimePicker().getTime();
        Duration duration = Duration.ofMinutes((Integer) durationSpinner.getValue());
        return new Event(isDone, name, categories, location, date, time, duration);
    }
}
