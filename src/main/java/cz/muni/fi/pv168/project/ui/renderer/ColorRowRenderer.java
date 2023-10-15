package cz.muni.fi.pv168.project.ui.renderer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import cz.muni.fi.pv168.project.model.CategoryColor;

public class ColorRowRenderer extends DefaultTableCellRenderer {
    private final Map<CategoryColor, Color> colorMap = new HashMap<>();

    public ColorRowRenderer() {
        for (CategoryColor categoryColor : CategoryColor.values()) {
            colorMap.put(categoryColor, categoryColor.getColor());
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof CategoryColor categoryColor) {
            Color backgroundColor = colorMap.get(categoryColor);
            if (backgroundColor != null) {
                component.setBackground(backgroundColor);
            }
        }

        return component;
    }
}