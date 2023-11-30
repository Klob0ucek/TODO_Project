package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class EventColorRenderer extends DefaultTableCellRenderer {
    private final CrudHolder crudHolder;

    public EventColorRenderer(CrudHolder crudHolder) {
        this.crudHolder = crudHolder;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 2) {
            List<String> names = Arrays.stream(value.toString().split(", ")).toList();
            if (names.isEmpty()) {
                component.setBackground(null);
                return component;
            }

            List<Color> colors = crudHolder.getCategories().stream()
                    .filter(c -> names.contains(c.getName()))
                    .map(c -> c.getColor().getColor())
                    .toList();
            if (isSelected) {
                component.setBackground(calculateGradientColor(colors).darker());
            } else {
                component.setBackground(calculateGradientColor(colors));
            }
        } else {
            component.setBackground(null);
        }
        return component;
    }

    private static Color calculateGradientColor(List<Color> colors) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        for (Color color : colors) {
            redSum += color.getRed();
            greenSum += color.getGreen();
            blueSum += color.getBlue();
        }

        int averageRed = redSum / colors.size();
        int averageGreen = greenSum / colors.size();
        int averageBlue = blueSum / colors.size();

        return new Color(averageRed, averageGreen, averageBlue);
    }

}
