package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.model.Template;

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
                Column.editable("Duration", LocalTime.class, Template::getDuration, Template::setDuration)
        );
        rows.add(new Template("English classes", false, "Lesson",
                List.of(new Category("School", CategoryColor.GREEN)), "MUNI FI", LocalTime.of(8, 30), LocalTime.of(9, 0)));
        rows.add(new Template("Running", false, "Morning run",
                List.of(new Category("Free Time", CategoryColor.PINK), new Category("Health", CategoryColor.RED)), null, null, LocalTime.of(6, 30)));
    }
}
