package cz.muni.fi.pv168.todo.project.ui.dialog;

import com.github.lgooddatepicker.components.TimePicker;
import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.Template;
import cz.muni.fi.pv168.todo.project.ui.model.ComboBoxModelAdapter;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.time.Duration;
import java.util.List;

public class AddTemplateDialog extends EntityDialog<Template> {
    private final JTextField templateNameField = new JTextField();
    private final JTextField eventNameField = new JTextField();
    private final ComboBoxModel<Category> categoryModel;
    private final JTextField locationField = new JTextField();
    private final TimePicker timePicker = new TimePicker();
    private final JSpinner durationSpinner = new JSpinner();

    private final Template template;

    public AddTemplateDialog(Template template, ListModel<Category> categoryModel) {
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.template = template;
        addFields();
    }

    private void addFields() {
        add("Template name:", templateNameField);
        add("Event name:", eventNameField);
        add("Category:", new JComboBox<>(categoryModel));
        add("Location:", locationField);
        add("Time:", timePicker);
        add("Duration in minutes", durationSpinner);
    }

    @Override
    Template getEntity() {
        template.setTemplateName(templateNameField.getText());
        template.setDone(false);
        template.setName(eventNameField.getText());
        template.setCategories(List.of((Category) categoryModel.getSelectedItem()));
        template.setLocation(locationField.getText());
        template.setTime(timePicker.getTime());
        template.setDuration(Duration.ofMinutes((Integer) durationSpinner.getValue()));
        return template;
    }
}
