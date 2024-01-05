package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.DateTimePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.CheckGroup;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.OptionGroupInitializer;
import cz.muni.fi.pv168.project.todoapp.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.todoapp.ui.model.ListModel;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.ComboBoxRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomDatePickerSettings;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomTimePickerSettings;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.time.Duration;
import java.util.List;

public class EventDialog extends EntityDialog<Event> {
    private final ComboBoxModel<Template> templateListModel;
    private final JComboBox<Template> templateComboBox = new JComboBox<>();
    private final JCheckBox doneField = new JCheckBox();
    private final List<Category> categories;
    private final JMenuBar categoriesMenuBar = new JMenuBar();
    private final CheckGroup categoryOptions = new CheckGroup();
    private final JTextField nameField = new JTextField("", 20);
    private final JTextField locationField = new JTextField();
    private final DateTimePicker dateTimePicker = new DateTimePicker(
            CustomDatePickerSettings.getSettings(),
            CustomTimePickerSettings.getSettings()
    );
    private final ComboBoxModel<Interval> intervalListModel;
    private final JComboBox<Interval> intervalComboBox = new JComboBox<>();
    private final JSpinner quantitySpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));
    private final JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));

    private Event event;

    public EventDialog(ListModel<Template> templateListModel, ListModel<Interval> intervalListModel,
                       List<Category> categories) {
        this.templateListModel = new ComboBoxModelAdapter<>(templateListModel);
        this.intervalListModel = new ComboBoxModelAdapter<>(intervalListModel);
        this.categories = categories;
        OptionGroupInitializer.initializer("Categories", JCheckBoxMenuItem::new,
                categories.stream().map(Category::getName).toList(), categoriesMenuBar, categoryOptions);
        addFields();
    }

    public EventDialog(ListModel<Template> templateListModel, ListModel<Interval> intervalListModel,
                       List<Category> categories, Event event) {
        this(templateListModel, intervalListModel, categories);
        makeCopy(event);
        setFields(event);
    }

    private void makeCopy(Event event) {
        this.event = new Event(
                event.getGuid(),
                event.isDone(),
                event.getName(),
                event.getCategories(),
                event.getLocation(),
                event.getDate(),
                event.getTime(),
                event.getDuration()
        );
    }

    private void setFields(Event event) {
        doneField.setSelected(event.isDone());
        nameField.setText(event.getName());
        event.getCategories().forEach(c -> categoryOptions.getCheckBoxes().get(categories.indexOf(c)).setState(true));
        locationField.setText(event.getLocation());
        dateTimePicker.datePicker.setDate(event.getDate());
        dateTimePicker.timePicker.setTime(event.getTime());
        if (event.getDuration() != null) {
            durationSpinner.setValue(event.getDuration().toMinutes());
        }
    }

    private void addFields() {
        addTwoComponentPanel("From template:", templateComboBoxSetup(), "", clearButtonSetup());
        addThreeComponentPanel("Done?", doneField, "Name:", nameField, "", categoriesMenuBar);
        add("Location:", locationField);
        add("Date and time:", dateTimePicker);
        addThreeComponentPanel("Interval:", intervalComboBoxSetup(), "Quantity:", quantitySpinnerSetup(),
                "Duration in minutes:", durationSpinner);
    }

    private JComboBox<Template> templateComboBoxSetup() {
        templateComboBox.setModel(templateListModel);
        templateComboBox.setRenderer(new ComboBoxRenderer());
        templateComboBox.addActionListener(e -> {
            Template template = (Template) templateComboBox.getSelectedItem();
            if (template == null) return;

            categoryOptions.setDefault();
            intervalComboBox.setSelectedIndex(-1);
            quantitySpinner.setEnabled(false);
            doneField.setSelected(template.isDone());
            nameField.setText(template.getName());
            locationField.setText(template.getLocation());
            dateTimePicker.timePicker.setTime(template.getTime());

            if (template.getCategories() != null) {
                template.getCategories().forEach(c -> categoryOptions.getCheckBoxes()
                        .get(categories.indexOf(c)).setState(true));
            }

            if (template.getDuration() != null) {
                durationSpinner.setValue(template.getDuration().toMinutes());
            }
        });

        return templateComboBox;
    }

    private JComboBox<Interval> intervalComboBoxSetup() {
        intervalComboBox.setModel(intervalListModel);
        intervalComboBox.setRenderer(new ComboBoxRenderer());
        intervalComboBox.addActionListener(e -> {
            if (!quantitySpinner.isEnabled()) {
                quantitySpinner.setEnabled(true);
            }
            quantitySpinner.setValue(0);
        });
        return intervalComboBox;
    }

    private JSpinner quantitySpinnerSetup() {
        quantitySpinner.setEnabled(false);
        quantitySpinner.addChangeListener(e -> {
            Interval interval = (Interval) intervalComboBox.getSelectedItem();
            if (interval != null) {
                var duration = interval.getDuration().toMinutes() * (((Number) quantitySpinner.getValue()).longValue());
                durationSpinner.setValue(duration);
            }
        });
        return quantitySpinner;
    }

    private JButton clearButtonSetup() {
        JButton clearButton = new JButton("Clear");

        clearButton.addActionListener(e -> {
            templateComboBox.setSelectedIndex(-1);
            doneField.setSelected(false);
            nameField.setText("");
            categoryOptions.setDefault();
            locationField.setText("");
            dateTimePicker.clear();
            intervalComboBox.setSelectedIndex(-1);
            quantitySpinner.setEnabled(false);
            durationSpinner.setValue(0);
        });

        return clearButton;
    }

    @Override
    Event getEntity() {
        return new Event(doneField.isSelected(), nameField.getText(), categories, locationField.getText(),
                dateTimePicker.getDatePicker().getDate(), dateTimePicker.getTimePicker().getTime(),
                Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue()));
    }
}
