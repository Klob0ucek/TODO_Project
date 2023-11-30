package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TemplateColorRenderer extends DefaultTableCellRenderer {
    private final CrudHolder crudHolder;

    public TemplateColorRenderer(CrudHolder crudHolder) {
        this.crudHolder = crudHolder;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 3) {
            if (value == null || value.equals("")) {
                this.setBackground(null);
                return this;
            }
            List<String> names = Arrays.stream(value.toString().split(", ")).toList();

            List<Color> colors = crudHolder.getCategories().stream()
                    .filter(c -> names.contains(c.getName()))
                    .map(c -> c.getColor().getColor())
                    .toList();
            if (isSelected) {
                this.setBackground(calculateGradientColor(colors).darker());
            } else {
                this.setBackground(calculateGradientColor(colors));
            }
        } else {
            this.setBackground(null);
        }
        return this;
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
