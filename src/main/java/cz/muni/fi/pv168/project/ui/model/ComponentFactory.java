package cz.muni.fi.pv168.project.ui.model;

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
        CategoryTableModel model = new CategoryTableModel();
        JTable table = new JTable(model);

        var genderComboBox = new JComboBox<>(CategoryColor.values());
        table.setDefaultEditor(CategoryColor.class, new DefaultCellEditor(genderComboBox));
        model.setRowBackgroundColors(table);
        return table;
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
