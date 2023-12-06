package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public class ComboBoxRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Template template) {
            setText(template.getTemplateName());
        }
        if (value instanceof Interval interval) {
            String text = interval.getName();
            if (interval.getAbbreviation() != null) {
                text += " (" + interval.getAbbreviation() + ")";
            }
            setText(text);
        }
        if (value instanceof CategoryColor color) {
            this.setBackground(color.getColor());
        }
        return this;
    }
}
