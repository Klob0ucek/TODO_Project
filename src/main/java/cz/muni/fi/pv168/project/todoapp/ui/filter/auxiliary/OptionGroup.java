package cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary;

import javax.swing.JMenuItem;

public interface OptionGroup<T extends JMenuItem> {
    void add(T option);

    void setDefault();
}
