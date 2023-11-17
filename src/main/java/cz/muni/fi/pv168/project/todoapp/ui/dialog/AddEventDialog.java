package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.DateTimePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.CheckGroup;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.OptionGroupInitializer;
import cz.muni.fi.pv168.project.todoapp.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomDatePickerSettings;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomTimePickerSettings;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddEventDialog extends EntityDialog<Event> {
    private final JPanel topPanel = new JPanel();
    private final JCheckBox doneField = new JCheckBox();
    private final JTextField nameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final List<Category> categories;
    private final JMenuBar categoriesMenuBar = new JMenuBar();
    private final CheckGroup categoryOptions = new CheckGroup();
    private final JTextField locationField = new JTextField();
    private final DateTimePicker dateTimePicker = new DateTimePicker(
            CustomDatePickerSettings.getSettings(),
            CustomTimePickerSettings.getSettings()
    );
    private final JSpinner durationSpinner = new JSpinner();

    public AddEventDialog(ListModel<Category> categoryModel, List<Category> categories) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.categories = categories;
        initCategories(categories.stream().map(Category::getName).toList());
        topPanelSetup();
        addFields();
    }

    private void addFields() {
        panel.add(topPanel);
        //add("Category:", new JComboBox<>(categoryModel));
        add("Another categories:", categoriesMenuBar);
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

    private void initCategories(List<String> categories) {
        OptionGroupInitializer.initializer(
                "Additional categories",
                JCheckBoxMenuItem::new,
                categories,
                categoriesMenuBar,
                categoryOptions
        );
    }

    @Override
    Event getEntity() {
        boolean isDone = doneField.isSelected();
        String name = nameField.getText();
        //List<Category> categories = List.of((Category) categoryModel.getSelectedItem());
        List<Category> categories = getCategoriesFromCheckBoxes(categoryOptions.getCheckBoxes());
        String location = locationField.getText();
        LocalDate date = dateTimePicker.getDatePicker().getDate();
        LocalTime time = dateTimePicker.getTimePicker().getTime();
        Duration duration = Duration.ofMinutes((Integer) durationSpinner.getValue());
        return new Event(isDone, name, categories, location, date, time, duration);
    }

    private List<Category> getCategoriesFromCheckBoxes(List<JCheckBoxMenuItem> checkBoxes) {
        List<Category> result = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            if (checkBoxes.get(i).getState()) {
                result.add(categories.get(i));
            }
        }

        return result;
    }
}
