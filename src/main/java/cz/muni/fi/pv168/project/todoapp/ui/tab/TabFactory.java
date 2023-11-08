package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ComponentFactory;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;

import javax.swing.*;
import java.util.function.Supplier;

public class TabFactory {
    private TabFactory() {
        // not meant for instantiating
    }

    public static GeneralTab createEventsTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            Supplier<CategoryTableModel> categoryTableModelSupplier
    ) {
        return new EventsTab.BuildTemplate()
                .addTabDetails(
                        "Events",
                        null,
                        ComponentFactory.createScheduleTable(),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .addCategoryTableSupplier(categoryTableModelSupplier)
                .build();
    }

    public static GeneralTab createCategoriesTab(
            JFrame frame,
            ToolBarManager toolBarManager
    ) {
        return new CategoriesTab.BuildTemplate()
                .addTabDetails(
                        "Categories",
                        null,
                        ComponentFactory.createCategoryTable(),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createTemplatesTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            Supplier<CategoryTableModel> categoryTableModelSupplier
    ) {
        return new TemplatesTab.BuildTemplate()
                .addTabDetails(
                        "Templates",
                        null,
                        ComponentFactory.createTemplateTable(),
                        frame,
                        null
                )
                .addToolBarManager(toolBarManager)
                .addCategoryTableSupplier(categoryTableModelSupplier)
                .build();
    }

    public static GeneralTab createIntervalsTab(
            JFrame frame,
            ToolBarManager toolBarManager
    ) {
        return new IntervalsTab.BuildTemplate()
                .addTabDetails(
                        "Intervals",
                        null,
                        ComponentFactory.createIntervalTable(),
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
