package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterDoneValues;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class SpecialFilterDoneValuesRenderer extends AbstractRenderer<SpecialFilterDoneValues> {

    public SpecialFilterDoneValuesRenderer() {
        super(SpecialFilterDoneValues.class);
    }

    protected void updateLabel(JLabel label, SpecialFilterDoneValues value) {
        if (value == null) {
            return;
        }
        switch (value) {
            case ALL -> renderBoth(label);
            case DONE -> label.setText(SpecialFilterDoneValues.DONE.name());
            case PLANNED -> label.setText(SpecialFilterDoneValues.PLANNED.name());
            default -> label.setText("Default");
        }
    }

    private static void renderBoth(JLabel label) {
        label.setText("(BOTH)");
        label.setFont(label.getFont().deriveFont(Font.ITALIC));
        label.setForeground(Color.GRAY);
    }
}

