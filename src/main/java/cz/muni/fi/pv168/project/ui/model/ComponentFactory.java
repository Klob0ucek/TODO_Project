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
        throw new UnknownError("Not implemented");
    }

    public static JTable createTemplateTable() {
        throw new UnknownError("Not implemented");
    }

    public static JTable createIntervalTable() {
        throw new UnknownError("Not implemented");
    }

    public static JComponent createHelp() {
        throw new UnknownError("Not implemented");
    }
}
