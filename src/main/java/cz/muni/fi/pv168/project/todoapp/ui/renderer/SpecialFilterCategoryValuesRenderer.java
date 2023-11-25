package cz.muni.fi.pv168.project.todoapp.ui.renderer;

import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterCategoryValues;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class SpecialFilterCategoryValuesRenderer extends AbstractRenderer<SpecialFilterCategoryValues> {

    public SpecialFilterCategoryValuesRenderer() {
        super(SpecialFilterCategoryValues.class);
    }

    protected void updateLabel(JLabel label, SpecialFilterCategoryValues value) {
        switch (value) {
            case ALL -> renderBoth(label);
        }
    }

    private static void renderBoth(JLabel label) {
        label.setText("(ALL)");
        label.setFont(label.getFont().deriveFont(Font.ITALIC));
        label.setForeground(Color.GRAY);
    }
}

