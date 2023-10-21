package cz.muni.fi.pv168.project.todoapp.ui.tab;

import javax.swing.JTabbedPane;

import java.util.List;

public record TabHolder(JTabbedPane tabbedPane, List<GeneralTab> tabs) {

    public GeneralTab getCurrentTab() {
        return tabs.get(tabbedPane.getSelectedIndex());
    }

    public GeneralTab getTabAt(int index) {
        return tabs.get(index);
    }
}
