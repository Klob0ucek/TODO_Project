package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.TimePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
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
import javax.swing.BorderFactory;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class AddTemplateDialog extends EntityDialog<Template> {
    private final JPanel topPanel = new JPanel();
    private final JCheckBox doneField = new JCheckBox();
    private final JTextField templateNameField = new JTextField();
    private final JTextField eventNameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();
    private final TimePicker timePicker = new TimePicker();
    private final JSpinner durationSpinner = new JSpinner();

    public AddTemplateDialog(ListModel<Category> categoryModel) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        topPanelSetup();
        addFields();
    }

    private void addFields() {
        add("Template name:", templateNameField);
        panel.add(topPanel);
        add("Category:", new JComboBox<>(categoryModel));
        add("Location:", locationField);
        add("Time:", timePicker);
        add("Duration in minutes:", durationSpinner);
    }

    private void topPanelSetup() {
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        topPanel.add(new JLabel(" Done? "));
        topPanel.add(doneField);
        topPanel.add(new JLabel(" Event name: "));
        topPanel.add(eventNameField);
    }

    @Override
    Template getEntity() {
        String templateName = templateNameField.getText();
        boolean isDone = doneField.isSelected();
        String name = eventNameField.getText();
        List<Category> categories = List.of((Category) categoryModel.getSelectedItem());
        String location = locationField.getText();
        LocalTime time = timePicker.getTime();
        Duration duration = Duration.ofMinutes((Integer) durationSpinner.getValue());
        return new Template(templateName, isDone, name, categories, location, time, duration);
    }
}
