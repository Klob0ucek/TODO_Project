package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import com.github.lgooddatepicker.components.TimePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.CheckGroup;
import cz.muni.fi.pv168.project.todoapp.ui.auxiliary.OptionGroupInitializer;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomTimePickerSettings;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private final Template template = new Template();

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
        this.template.setGuid(template.getGuid());
        this.template.setTemplateName(template.getTemplateName());
        this.template.setDone(template.isDone());
        this.template.setName(template.getName());
        this.template.setLocation(template.getLocation());
        this.template.setTime(template.getTime());
        this.template.setCategories(template.getCategories());
        this.template.setDuration(template.getDuration());
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
        template.setTemplateName(templateNameField.getText());
        template.setDone(doneField.isSelected());
        template.setName(eventNameField.getText());
        List<JCheckBoxMenuItem> checkBoxes = categoryOptions.getCheckBoxes();
        List<Category> categories = IntStream.range(0, checkBoxes.size())
                .filter(i -> checkBoxes.get(i).getState())
                .mapToObj(this.categories::get)
                .collect(Collectors.toList());
        template.setCategories(categories);
        template.setLocation(locationField.getText());
        template.setTime(timePicker.getTime());
        template.setDuration(Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue()));
        return template;
    }
}
