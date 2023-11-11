package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.ComponentFactory;
import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;

import javax.swing.*;

public class TabFactory {
    private TabFactory() {
        // not meant for instantiating
    }

    public static GeneralTab createEventsTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            ScheduleTableModel model,
            CrudHolder crudHolder
    ) {
        return new EventsTab.BuildTemplate()
                .addTabDetails(
                        "Events",
                        null,
                        ComponentFactory.createScheduleTable(model),
                        frame,
                        null,
                        crudHolder
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createCategoriesTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            CategoryTableModel model,
            CrudHolder crudHolder
    ) {
        return new CategoriesTab.BuildTemplate()
                .addTabDetails(
                        "Categories",
                        null,
                        ComponentFactory.createCategoryTable(model),
                        frame,
                        null,
                        crudHolder
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createTemplatesTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            TemplateTableModel model,
            CrudHolder crudHolder
    ) {
        return new TemplatesTab.BuildTemplate()
                .addTabDetails(
                        "Templates",
                        null,
                        ComponentFactory.createTemplateTable(model),
                        frame,
                        null,
                        crudHolder
                )
                .addToolBarManager(toolBarManager)
                .build();
    }

    public static GeneralTab createIntervalsTab(
            JFrame frame,
            ToolBarManager toolBarManager,
            IntervalTableModel model,
            CrudHolder crudHolder
    ) {
        return new IntervalsTab.BuildTemplate()
                .addTabDetails(
                        "Intervals",
                        null,
                        ComponentFactory.createIntervalTable(model),
                        frame,
                        null,
                        crudHolder
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
                        null,
                        null
                )
                .addToolBarManager(toolBarManager)
                .build();
    }
}
