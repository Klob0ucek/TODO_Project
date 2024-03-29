package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Component;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorRowRenderer extends DefaultTableCellRenderer {
    private final Map<Integer, Color> rowMap = new HashMap<>();

    public ColorRowRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 0) {
            if (value instanceof CategoryColor categoryColor) {
                rowMap.put(row, ((CategoryColor) value).getColor());
            }
        }

        if (rowMap.get(row) != null) {
            if (isSelected) {
                component.setBackground(rowMap.get(row).darker());
            } else {
                component.setBackground(rowMap.get(row));
            }
        }
        return component;
    }
}