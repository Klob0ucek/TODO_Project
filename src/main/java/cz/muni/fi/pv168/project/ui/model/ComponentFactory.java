package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.ui.Tab;

import javax.swing.JComponent;
import javax.swing.JTable;

/**
 * Static factory class for components.
 */
public class ComponentFactory {
    private ComponentFactory() {
        // not meant for instancing
    }

    public static JComponent createTab(
            Tab tab
    ) {
        return switch (tab) {
            case EVENTS -> createScheduleTable();
            case CATEGORIES -> createCategoryTable();
            case TEMPLATES -> createTemplateTable();
            case INTERVALS -> createIntervalTable();
            case HELP -> createHelp();
        };
    }

    public static JTable createScheduleTable() {
        return new JTable(new ScheduleTableModel());
    }

    public static JTable createCategoryTable() {
        return new JTable(new CategoryTableModel());
    }

    public static JTable createTemplateTable() {
        return new JTable();  // TODO
    }

    public static JTable createIntervalTable() {
        return new JTable();  // TODO
    }

    public static JComponent createHelp() {
        return new JTable();  // TODO
    }
}
