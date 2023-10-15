package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.model.Category;
import cz.muni.fi.pv168.project.ui.renderer.ColorRowRenderer;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    private final List<Category> categories = new LinkedList<>();

    private final List<Column<Category, ?>> columns = List.of(
            Column.editable("Color", Color.class, Category::getColor, Category::setColor),
            Column.editable("Name", String.class, Category::getName, Category::setName)
    );

    public CategoryTableModel() {
        categories.add(new Category("Work", Color.PINK));
        categories.add(new Category("Exercise", Color.green));
        categories.add(new Category("Work", Color.RED));
    }


    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var category = getEntity(rowIndex);
        return columns.get(columnIndex).getValue(category);
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
        categories.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Category category) {
        int newRowIndex = categories.size();
        categories.add(category);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Category category) {
        int rowIndex = categories.indexOf(category);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void setRowBackgroundColors(JTable table) {
        table.setDefaultRenderer(Object.class, new ColorRowRenderer());
    }

    public Category getEntity(int rowIndex) {
        return categories.get(rowIndex);
    }
}
