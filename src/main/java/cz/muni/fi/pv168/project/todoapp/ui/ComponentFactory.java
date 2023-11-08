package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.HelpTab;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Static factory class for components.
 */
public class ComponentFactory {
    private ComponentFactory() {
        // not meant for instancing
    }

    private static void disableColumnDragging(JTable table) {
        table.getTableHeader().setReorderingAllowed(false);
    }

    private static void setColumnSorting(JTable table) {
        table.setAutoCreateRowSorter(true);
    }

    private static void setupTable(JTable table) {
        disableColumnDragging(table);
        setColumnSorting(table);
    }

    private static JTable createTableFromModel(AbstractTableModel model) {
        var table = new JTable(model);
        setupTable(table);
        return table;
    }

    public static JTable createScheduleTable() {
        return createTableFromModel(new ScheduleTableModel());
    }

    public static JTable createCategoryTable() {
        CategoryTableModel model = new CategoryTableModel();
        JTable table = new JTable(model);
        setupTable(table);

        var genderComboBox = new JComboBox<>(CategoryColor.values());
        table.setDefaultEditor(CategoryColor.class, new DefaultCellEditor(genderComboBox));
        model.setRowBackgroundColors(table);

        return table;
    }

    public static JTable createTemplateTable() {
        return createTableFromModel(new TemplateTableModel());
    }

    public static JTable createIntervalTable() {
        return createTableFromModel(new IntervalTableModel());
    }

    public static JComponent createHelp() {
        return new HelpTab();
    }
}
