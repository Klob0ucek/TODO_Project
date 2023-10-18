package cz.muni.fi.pv168.project.todoapp.ui.tab;

import javax.swing.JTabbedPane;

import java.util.List;

public class TabHolder {
    private final JTabbedPane tabbedPane;
    private final List<Tab> tabs;

    public TabHolder(
            JTabbedPane tabbedPane,
            List<Tab> tabs
    ) {
        this.tabbedPane = tabbedPane;
        this.tabs = tabs;
    }

    public Tab getCurrentTab() {
        return tabs.get(tabbedPane.getSelectedIndex());
    }

    public Tab getTabAt(int index) {
        return tabs.get(index);
    }
}
