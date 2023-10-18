package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.HelpTab;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;

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

    private static JTable setCommonTableSetting(JTable table) {
        // disable column dragging
        table.getTableHeader().setReorderingAllowed(false);
        // enable row sorting by clicking on column
        table.setAutoCreateRowSorter(true);
        return table;
    }

    public static JTable createScheduleTable() {
        return setCommonTableSetting(new JTable(new ScheduleTableModel()));
    }

    public static JTable createCategoryTable() {
        CategoryTableModel model = new CategoryTableModel();
        JTable table = new JTable(model);

        var genderComboBox = new JComboBox<>(CategoryColor.values());
        table.setDefaultEditor(CategoryColor.class, new DefaultCellEditor(genderComboBox));
        model.setRowBackgroundColors(table);

        return setCommonTableSetting(table);
    }

    public static JTable createTemplateTable() {
        return setCommonTableSetting(new JTable(new TemplateTableModel()));
    }

    public static JTable createIntervalTable() {
        return setCommonTableSetting(new JTable(new IntervalTableModel()));
    }

    public static JComponent createHelp() {
        return new HelpTab();
    }
}
