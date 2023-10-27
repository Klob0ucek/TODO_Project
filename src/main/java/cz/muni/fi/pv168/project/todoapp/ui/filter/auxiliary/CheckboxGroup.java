package cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary;

import javax.swing.JCheckBoxMenuItem;

import java.util.ArrayList;
import java.util.List;

public class CheckboxGroup {
    private final List<JCheckBoxMenuItem> checkBoxes = new ArrayList<>();

    public void add(JCheckBoxMenuItem checkBox) {
        checkBoxes.add(checkBox);
    }

    public void clearSelection() {
        for (var checkbox : checkBoxes) {
            checkbox.setSelected(false);
        }
    }
}
