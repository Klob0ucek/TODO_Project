package cz.muni.fi.pv168.project.ui.tab;

import javax.swing.JTabbedPane;

public class TabHolder {
    private final JTabbedPane tabbedPane;
    private static final Tab[] tabValues;

    static {
        tabValues = Tab.values();
    }

    public TabHolder(
            JTabbedPane tabbedPane
    ) {
        this.tabbedPane = tabbedPane;
    }

    public Tab getCurrentTab() {
        return tabValues[tabbedPane.getSelectedIndex()];
    }
}
