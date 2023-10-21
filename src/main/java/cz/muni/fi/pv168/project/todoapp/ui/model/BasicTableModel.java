package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.model.Category;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.CategoryListRenderer;

import cz.muni.fi.pv168.todo.project.ui.renderer.DurationRenderer;

import javax.swing.table.AbstractTableModel;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public abstract class BasicTableModel<T> extends AbstractTableModel {
    protected final List<T> rows = new LinkedList<>();
    protected List<Column<T, ?>> columns;

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = columns.get(columnIndex).getValue(getEntity(rowIndex));

        if (o instanceof List && !((List<?>) o).isEmpty() && ((List<?>) o).get(0) instanceof Category) {
            return CategoryListRenderer.renderListCategory((List<Category>) o);
        } else if (o instanceof Duration) {
            return DurationRenderer.renderDuration((Duration) o);
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

    public T getEntity(int rowIndex) {
        return rows.get(rowIndex);
    }

    public void deleteRow(int rowIndex) {
        rows.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(T row) {
        int newRowIndex = rows.size();
        rows.add(row);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(T row) {
        int rowIndex = rows.indexOf(row);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
