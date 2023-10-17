package cz.muni.fi.pv168.todo.project.ui;

import cz.muni.fi.pv168.todo.project.model.CategoryColor;
import cz.muni.fi.pv168.todo.project.ui.model.*;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;

/**
 * Static factory class for components.
 */
public class ComponentFactory {
    private ComponentFactory() {
        // not meant for instancing
    }

    private static JTable disableColumnDragging(
            JTable table
    ) {
        table.getTableHeader().setReorderingAllowed(false);
        return table;
    }

    public static JTable createScheduleTable() {
        return disableColumnDragging(new JTable(new ScheduleTableModel()));
    }

    public static JTable createCategoryTable() {
        CategoryTableModel model = new CategoryTableModel();
        JTable table = new JTable(model);

        var genderComboBox = new JComboBox<>(CategoryColor.values());
        table.setDefaultEditor(CategoryColor.class, new DefaultCellEditor(genderComboBox));
        model.setRowBackgroundColors(table);

        return disableColumnDragging(table);
    }

    public static JTable createTemplateTable() {
        return new JTable(new TemplateTableModel());
    }

    public static JTable createIntervalTable() {
        return disableColumnDragging(new JTable(new IntervalTableModel()));
    }

    public static JComponent createHelp() {
        return new HelpTab();
    }
}
