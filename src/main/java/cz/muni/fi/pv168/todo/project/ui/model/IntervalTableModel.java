package cz.muni.fi.pv168.todo.project.ui.model;

import cz.muni.fi.pv168.todo.project.model.Interval;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

public class IntervalTableModel extends AbstractTableModel {
    private final List<Interval> intervals = new LinkedList<>();

    private final List<Column<Interval, ?>> columns = List.of(
            Column.editable("Name", String.class, Interval::getName, Interval::setName),
            Column.editable("Abbreviation", String.class, Interval::getAbbreviation, Interval::setAbbreviation),
            Column.editable("Duration in minutes", Integer.class, Interval::getDuration, Interval::setDuration)
    );

    public IntervalTableModel() {
        intervals.add(new Interval("Vyučovací hodina", "vh", 45));
    }

    @Override
    public int getRowCount() {
        return intervals.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var interval = getEntity(rowIndex);
        return columns.get(columnIndex).getValue(interval);
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
        var interval = getEntity(rowIndex);
        columns.get(columnIndex).setValue(value, interval);
    }

    public void deleteRow(int rowIndex) {
        intervals.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Interval interval) {
        int newRowIndex = intervals.size();
        intervals.add(interval);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Interval interval) {
        int rowIndex = intervals.indexOf(interval);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Interval getEntity(int rowIndex) {
        return intervals.get(rowIndex);
    }
}
