package cz.muni.fi.pv168.project.todoapp.ui.auxiliary;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;


public class RadioGroup implements OptionGroup<JRadioButtonMenuItem> {
    private final ButtonGroup radios = new ButtonGroup();
    private JRadioButtonMenuItem firstOne = null;

    @Override
    public void add(JRadioButtonMenuItem option) {
        firstOne = firstOne == null ? option : firstOne;
        radios.add(option);
    }

    @Override
    public void setDefault() {
        firstOne.setSelected(true);
    }
}
