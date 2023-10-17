package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Category;
import cz.muni.fi.pv168.todo.project.model.CategoryColor;
import cz.muni.fi.pv168.todo.project.model.Event;

import javax.swing.table.AbstractTableModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ScheduleTableModel extends AbstractTableModel {
    private final List<Event> events = new LinkedList<>();

    private final List<Column<Event, ?>> columns = List.of(
            Column.editable("Done?", Boolean.class, Event::isDone, Event::setDone),
            Column.editable("Name", String.class, Event::getName, Event::setName),
            Column.editable("Category", List.class, Event::getCategories, Event::setCategories),
            Column.editable("Location", String.class, Event::getLocation, Event::setLocation),
            Column.editable("Date", LocalDate.class, Event::getDate, Event::setDate),
            Column.editable("Time", LocalTime.class, Event::getTime, Event::setTime),
            Column.editable("Duration", LocalTime.class, Event::getDuration, Event::setDuration)

    );

    public ScheduleTableModel() {
        // Local[Date|Time] isn't really editable

        LocalDate date = LocalDate.of(2023, 10, 10);
        LocalTime time = LocalTime.of(10, 0);
        LocalTime duration = LocalTime.of(4, 0);
        events.add(
                new Event(
                        false,
                        "Tennis",
                        List.of(new Category("Sport", CategoryColor.BLUE)),
                        "Tennis Hala Lužánky",
                        date, time, duration
                )
        );
    }

    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event event = getEntity(rowIndex);
        Object o = columns.get(columnIndex).getValue(event);
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
        events.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Event event) {
        int newRowIndex = events.size();
        events.add(event);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Event event) {
        int rowIndex = events.indexOf(event);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Event getEntity(int rowIndex) {
        return events.get(rowIndex);
    }
}
