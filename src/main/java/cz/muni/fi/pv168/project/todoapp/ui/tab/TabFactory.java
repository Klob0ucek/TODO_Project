package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ComponentFactory;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;

import javax.swing.JFrame;
import java.util.function.Supplier;

public class TabFactory {
    private TabFactory() {
        // not meant for instantiating
    }

    public static GeneralTab createEventsTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            ScheduleTableModel model,
            Supplier<CategoryTableModel> categoryTableModelSupplier
    ) {
        return new EventsTab.BuildTemplate()
                .addTabDetails(
                        "Events",
                        null,
                        ComponentFactory.createScheduleTable(model),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .addCategoryTableSupplier(categoryTableModelSupplier)
                .build();
    }

    public static GeneralTab createCategoriesTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            CategoryTableModel model
    ) {
        return new CategoriesTab.BuildTemplate()
                .addTabDetails(
                        "Categories",
                        null,
                        ComponentFactory.createCategoryTable(model),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createTemplatesTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            TemplateTableModel model,
            Supplier<CategoryTableModel> categoryTableModelSupplier
    ) {
        return new TemplatesTab.BuildTemplate()
                .addTabDetails(
                        "Templates",
                        null,
                        ComponentFactory.createTemplateTable(model),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .addCategoryTableSupplier(categoryTableModelSupplier)
                .build();
    }

    public static GeneralTab createIntervalsTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            IntervalTableModel model
    ) {
        return new IntervalsTab.BuildTemplate()
                .addTabDetails(
                        "Intervals",
                        null,
                        ComponentFactory.createIntervalTable(model),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createHelpTab(
            JFrame frame,
            ToolBarManager toolBarManager
    ) {
        return new HelpTab.BuildTemplate()
                .addTabDetails(
                        "Help",
                        null,
                        ComponentFactory.createHelp(),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }
}
