package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.model.Template;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class TemplateTableModel extends BasicTableModel<Template> {

    public TemplateTableModel() {
        columns = List.of(
                Column.readonly("Template Name", String.class, Template::getTemplateName),
                Column.readonly("Done?", Boolean.class, Template::isDone),
                Column.readonly("Event Name", String.class, Template::getName),
                Column.readonly("Categories", List.class, Template::getCategories),
                Column.readonly("Location", String.class, Template::getLocation),
                Column.readonly("Time", LocalTime.class, Template::getTime),
                Column.readonly("Duration", Duration.class, Template::getDuration)
        );
        rows.add(new Template("English classes", false, "Lesson",
                List.of(new Category("School", CategoryColor.GREEN)), "MUNI FI", LocalTime.of(8, 30), Duration.ofMinutes(45)));
        rows.add(new Template("Running", false, "Morning run",
                List.of(new Category("Free Time", CategoryColor.PINK), new Category("Health", CategoryColor.RED)), null, null, Duration.ofMinutes(75)));
    }
}
