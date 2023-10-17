package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.CategoryColor;
import cz.muni.fi.pv168.todo.project.model.Template;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class TemplateTableModel extends BasicTableModel<Template> {

    public TemplateTableModel() {
        columns = List.of(
                Column.editable("Template Name", String.class, Template::getTemplateName, Template::setTemplateName),
                Column.editable("Event Name", String.class, Template::getName, Template::setName),
                Column.editable("Categories", List.class, Template::getCategories, Template::setCategories),
                Column.editable("Location", String.class, Template::getLocation, Template::setLocation),
                Column.editable("Time", LocalTime.class, Template::getTime, Template::setTime),
                Column.editable("Duration", Duration.class, Template::getDuration, Template::setDuration)
        );
        rows.add(new Template("English classes", false, "Lesson",
                List.of(new Category("School", CategoryColor.GREEN)), "MUNI FI", LocalTime.of(8, 30), Duration.ofMinutes(45)));
        rows.add(new Template("Running", false, "Morning run",
                List.of(new Category("Free Time", CategoryColor.PINK), new Category("Health", CategoryColor.RED)), null, null, Duration.ofMinutes(45)));
    }
}
