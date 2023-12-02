package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.util.ColorMixer;
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
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 2) {
            List<String> names = Arrays.stream(value.toString().split(", ")).toList();
            if (names.isEmpty()) {
                return this;
            }

            List<Color> colors = crudHolder.getCategories().stream()
                    .filter(c -> names.contains(c.getName()))
                    .map(c -> c.getColor().getColor())
                    .toList();
            if (colors.isEmpty()) {
                return this;
            }
            if (isSelected) {
                this.setBackground(ColorMixer.calculateGradient(colors).darker());
            } else {
                this.setBackground(ColorMixer.calculateGradient(colors));
            }
        } else if (!isSelected) {
            this.setBackground(null);
        }
        return this;
    }
}
