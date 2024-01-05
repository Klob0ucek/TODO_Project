package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.TimePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.CheckGroup;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.OptionGroupInitializer;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomTimePickerSettings;

import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.time.Duration;
import java.util.List;

public class TemplateDialog extends EntityDialog<Template> {
    private final JCheckBox doneField = new JCheckBox();
    private final JTextField templateNameField = new JTextField();
    private final JTextField eventNameField = new JTextField("", 20);
    private final List<Category> categories;
    private final JMenuBar categoriesMenuBar = new JMenuBar();
    private final CheckGroup categoryOptions = new CheckGroup();
    private final JTextField locationField = new JTextField();
    private final TimePicker timePicker = new TimePicker(CustomTimePickerSettings.getSettings());
    private final JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));

    private Template template;

    public TemplateDialog(List<Category> categories) {
        this.categories = categories;
        OptionGroupInitializer.initializer("Categories", JCheckBoxMenuItem::new,
                categories.stream().map(Category::getName).toList(), categoriesMenuBar, categoryOptions);
        addFields();
    }

    public TemplateDialog(List<Category> categories, Template template) {
        this(categories);
        makeCopy(template);
        setFields(template);
    }

    private void makeCopy(Template template) {
        this.template = new Template(
                template.getGuid(),
                template.getTemplateName(),
                template.isDone(),
                template.getName(),
                template.getCategories(),
                template.getLocation(),
                template.getTime(),
                template.getDuration()
        );
    }

    private void setFields(Template template) {
        templateNameField.setText(template.getTemplateName());
        doneField.setSelected(template.isDone());
        eventNameField.setText(template.getName());
        locationField.setText(template.getLocation());
        timePicker.setTime(template.getTime());

        if (template.getCategories() != null) {
            template.getCategories()
                    .forEach(c -> categoryOptions.getCheckBoxes().get(categories.indexOf(c)).setState(true));
        }

        if (template.getDuration() != null) {
            durationSpinner.setValue(template.getDuration().toMinutes());
        }
    }

    private void addFields() {
        add("Template name:", templateNameField);
        addThreeComponentPanel("Done?", doneField, "Name:", eventNameField, "", categoriesMenuBar);
        add("Location:", locationField);
        add("Time:", timePicker);
        add("Duration in minutes:", durationSpinner);
    }

    @Override
    Template getEntity() {
        return new Template(templateNameField.getText(), doneField.isSelected(), eventNameField.getText(), categories, locationField.getText(),
                timePicker.getTime(), Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue()));
    }
}
