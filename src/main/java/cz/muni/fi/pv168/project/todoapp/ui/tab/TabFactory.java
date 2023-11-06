package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ComponentFactory;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;

public class TabFactory {
    private TabFactory() {
        // not meant for instantiating
    }

    public static GeneralTab createEventsTab(
            ToolBarManager toolBarManager
    ) {
        return new EventsTab.BuildTemplate()
                .addTabDetails(
                        "Events",
                        null,
                        ComponentFactory.createScheduleTable(),
                        null
                )
                .addToolBarManager(toolBarManager)
                .addTableSupplier(null)
                .build();
    }

    public static GeneralTab createCategoriesTab(
            ToolBarManager toolBarManager
    ) {
        return new CategoriesTab.BuildTemplate()
                .addTabDetails(
                        "Categories",
                        null,
                        ComponentFactory.createCategoryTable(),
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createTemplatesTab(
            ToolBarManager toolBarManager
    ) {
        return new TemplatesTab.BuildTemplate()
                .addTabDetails(
                        "Templates",
                        null,
                        ComponentFactory.createTemplateTable(),
                        null
                )
                .addToolBarManager(toolBarManager)
                .addTableSupplier(null)
                .build();
    }

    public static GeneralTab createIntervalsTab(
            ToolBarManager toolBarManager
    ) {
        return new IntervalsTab.BuildTemplate()
                .addTabDetails(
                        "Intervals",
                        null,
                        ComponentFactory.createIntervalTable(),
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createHelpTab(
            ToolBarManager toolBarManager
    ) {
        return new HelpTab.BuildTemplate()
                .addTabDetails(
                        "Help",
                        null,
                        ComponentFactory.createHelp(),
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }
}
