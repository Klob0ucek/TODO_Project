package cz.muni.fi.pv168.project.todoapp.ui.tab;

import javax.swing.JTabbedPane;

import java.util.List;

public class TabHolder {
    private final JTabbedPane tabbedPane;
    private final List<GeneralTab> tabs;

    public TabHolder(
            JTabbedPane tabbedPane,
            List<GeneralTab> tabs
    ) {
        this.tabbedPane = tabbedPane;
        this.tabs = tabs;
    }

    public GeneralTab getCurrentTab() {
        return tabs.get(tabbedPane.getSelectedIndex());
    }
}
