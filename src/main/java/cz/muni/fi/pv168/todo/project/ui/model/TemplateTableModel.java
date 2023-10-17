package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.CategoryColor;
import cz.muni.fi.pv168.todo.project.model.Template;

import javax.swing.table.AbstractTableModel;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class TemplateTableModel extends AbstractTableModel {
    private final List<Template> templates = new LinkedList<>();
    private final List<Column<Template, ?>> columns = List.of(
            Column.editable("Template Name", String.class, Template::getTemplateName, Template::setTemplateName),
            Column.editable("Event Name", String.class, Template::getName, Template::setName),
            Column.editable("Categories", List.class, Template::getCategories, Template::setCategories),
            Column.editable("Location", String.class, Template::getLocation, Template::setLocation),
            Column.editable("Time", LocalTime.class, Template::getTime, Template::setTime),
            Column.editable("Duration", LocalTime.class, Template::getDuration, Template::setDuration)
    );

    public TemplateTableModel() {
        templates.add(new Template("English classes", false, "Lesson",
                List.of(new Category("School", CategoryColor.GREEN)), "MUNI FI", LocalTime.of(8, 30), LocalTime.of(9, 0)));
        templates.add(new Template("Running", false, "Morning run",
                null, null, null, LocalTime.of(6, 30)));
    }

    public void addRow(Template template) {
        templates.add(template);
        fireTableRowsInserted(templates.size(), templates.size());
    }

    @Override
    public int getRowCount() {
        return templates.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Template template = getEntity(rowIndex);
        Object o = columns.get(columnIndex).getValue(template);
        if (o instanceof List) {
            return Category.listToString((List<Category>) o);
        }
        return o;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getColumnType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        var event = getEntity(rowIndex);
        columns.get(columnIndex).setValue(value, event);
    }

    public void deleteRow(int rowIndex) {
        templates.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void updateRow(Template template) {
        int rowIndex = templates.indexOf(template);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Template getEntity(int rowIndex) {
        return templates.get(rowIndex);
    }
}
