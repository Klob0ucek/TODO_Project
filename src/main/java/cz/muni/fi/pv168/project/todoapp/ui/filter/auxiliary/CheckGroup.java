package cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary;

import javax.swing.JCheckBoxMenuItem;

import java.util.ArrayList;
import java.util.List;

public class CheckGroup implements OptionGroup<JCheckBoxMenuItem> {
    private final List<JCheckBoxMenuItem> checkBoxes = new ArrayList<>();

    @Override
    public void add(JCheckBoxMenuItem checkBox) {
        checkBoxes.add(checkBox);
    }

    @Override
    public void setDefault() {
        for (var checkbox : checkBoxes) {
            checkbox.setSelected(false);
        }
    }
}
