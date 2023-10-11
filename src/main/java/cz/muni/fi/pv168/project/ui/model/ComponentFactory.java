package cz.muni.fi.pv168.project.ui.model;

import javax.swing.JComponent;
import javax.swing.JTable;

/**
 * Static factory class for components.
 */
public class ComponentFactory {
    private ComponentFactory() {
        // nope TODO
    }

    public static JTable createScheduleTable() {
        return new JTable(new ScheduleTableModel());
    }

    public static JTable createCategoryTable() {
        return new JTable();  // TODO
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
