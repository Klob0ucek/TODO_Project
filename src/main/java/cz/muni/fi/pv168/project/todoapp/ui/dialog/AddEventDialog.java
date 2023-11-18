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

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Box;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddEventDialog extends EntityDialog<Event> {
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
    private final JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));

    public AddEventDialog(ListModel<Template> templateListModel, ListModel<Interval> intervalListModel,
                          List<Category> categories) {
        this.templateListModel = new ComboBoxModelAdapter<>(templateListModel);
        this.intervalListModel = new ComboBoxModelAdapter<>(intervalListModel);
        this.categories = categories;
        OptionGroupInitializer.initializer("Categories", JCheckBoxMenuItem::new,
                categories.stream().map(Category::getName).toList(), categoriesMenuBar, categoryOptions);
        addFields();
    }

    private void addFields() {
        addTwoComponentPanel("From template:", templateComboBoxSetup(), "", resetButtonSetup());
        JPanel panel = addTwoComponentPanel("Done?", doneField, "Name:", nameField);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(categoriesMenuBar);
        add("Location:", locationField);
        add("Date and time:", dateTimePicker);
        addTwoComponentPanel("Interval:", intervalComboBoxSetup(), "Duration in minutes:", durationSpinner);
    }

    private JComboBox<Template> templateComboBoxSetup() {
        templateComboBox.setModel(templateListModel);
        templateComboBox.setRenderer(new ComboBoxRenderer());
        templateComboBox.addActionListener(e -> {
            Template template = (Template) templateComboBox.getSelectedItem();
            if (template == null) return;

            categoryOptions.setDefault();
            intervalComboBox.setSelectedIndex(-1);
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
            Interval interval = (Interval) intervalComboBox.getSelectedItem();
            if (interval == null) return;
            durationSpinner.setValue(interval.getDuration().toMinutes());
        });

        return intervalComboBox;
    }

    private JButton resetButtonSetup() {
        JButton resetButton = new JButton("Reset");

        resetButton.addActionListener(e -> {
            templateComboBox.setSelectedIndex(-1);
            doneField.setSelected(false);
            nameField.setText("");
            categoryOptions.setDefault();
            locationField.setText("");
            dateTimePicker.clear();
            intervalComboBox.setSelectedIndex(-1);
            durationSpinner.setValue(0);
        });

        return resetButton;
    }

    @Override
    Event getEntity() {
        boolean isDone = doneField.isSelected();
        List<JCheckBoxMenuItem> checkBoxes = categoryOptions.getCheckBoxes();
        List<Category> categories = IntStream.range(0, checkBoxes.size())
                .filter(i -> checkBoxes.get(i).getState())
                .mapToObj(this.categories::get)
                .collect(Collectors.toList());
        String name = nameField.getText();
        String location = locationField.getText();
        LocalDate date = dateTimePicker.getDatePicker().getDate();
        LocalTime time = dateTimePicker.getTimePicker().getTime();
        Duration duration = Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue());
        return new Event(isDone, name, categories, location, date, time, duration);
    }
}
