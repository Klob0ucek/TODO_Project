package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.Template;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CategoryCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.EventCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.IntervalCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.TemplateCrudService;
import cz.muni.fi.pv168.project.todoapp.business.service.export.GenericExportService;
import cz.muni.fi.pv168.project.todoapp.business.service.export.GenericImportService;
import cz.muni.fi.pv168.project.todoapp.business.service.export.JsonExporter;
import cz.muni.fi.pv168.project.todoapp.business.service.export.JsonImporter;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.data.ExampleData;
import cz.muni.fi.pv168.project.todoapp.storage.InMemoryRepository;
import cz.muni.fi.pv168.project.todoapp.ui.action.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.ImportAction;
import cz.muni.fi.pv168.project.todoapp.ui.filter.EventTableFilter;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.GeneralTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabFactory;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.table.TableRowSorter;

public class MainWindow {
    private final JFrame frame = createFrame();
    private final List<GeneralTab> tabs = new ArrayList<>();
    private ScheduleTableModel scheduleTableModel;
    private CategoryTableModel categoryTableModel;
    private TemplateTableModel templateTableModel;
    private IntervalTableModel intervalTableModel;
    private CrudHolder crudHolder;

    public MainWindow() {
        JComponent verticalToolBar = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);
        ToolBarManager toolBarManager = createCruds(verticalToolBar);

        TableRowSorter<ScheduleTableModel> rowSorter = new TableRowSorter<>(scheduleTableModel);
        rowSorter.toggleSortOrder(4);
        EventTableFilter eventTableFilter = new EventTableFilter(rowSorter, crudHolder);
        Filter filter = new Filter(crudHolder, eventTableFilter);

        createTabs(toolBarManager, tabbedPane, filter, rowSorter);

        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));
        tabHolder.getCurrentTab().updateToolBar();
        frame.add(filter.getFilterBar(), BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(verticalToolBar, BorderLayout.WEST);
        frame.add(createBottomLine(), BorderLayout.SOUTH);
        frame.pack();
    }

    private JLabel createBottomLine() {
        return new JLabel("BOTTOM-SIDE");
    }

    private ToolBarManager createCruds(JComponent verticalToolBar) {
        InMemoryRepository<Event> eventRepository = new InMemoryRepository<>(ExampleData.getEvents());
        InMemoryRepository<Category> categoryRepository = new InMemoryRepository<>(ExampleData.getCategories());
        InMemoryRepository<Template> templateRepository = new InMemoryRepository<>(ExampleData.getTemplates());
        InMemoryRepository<Interval> intervalRepository = new InMemoryRepository<>(ExampleData.getIntervals());

        var eventCrudService = new EventCrudService(eventRepository);
        var categoryCrudService = new CategoryCrudService(categoryRepository);
        var templateCrudService = new TemplateCrudService(templateRepository);
        var intervalCrudService = new IntervalCrudService(intervalRepository);

        var exportService = new GenericExportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonExporter()));
        var importService = new GenericImportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonImporter()));

        scheduleTableModel = new ScheduleTableModel(eventCrudService);
        categoryTableModel = new CategoryTableModel(categoryCrudService);
        templateTableModel = new TemplateTableModel(templateCrudService);
        intervalTableModel = new IntervalTableModel(intervalCrudService);
        ToolBarManager toolBarManager = new ToolBarManager(
                verticalToolBar,
                new ExportAction(frame, exportService),
                new ImportAction(frame, importService, this::refreshModels));

        this.crudHolder = new CrudHolder(eventCrudService, categoryCrudService, templateCrudService, intervalCrudService);
        return toolBarManager;
    }

    private void createTabs(
            ToolBarManager toolBarManager,
            JTabbedPane tabbedPane,
            Filter filter,
            RowSorter<ScheduleTableModel> rowSorter
    ) {

        tabs.addAll(
                List.of(
                        TabFactory.createEventsTab(frame, toolBarManager, scheduleTableModel, crudHolder, filter),
                        TabFactory.createCategoriesTab(frame, toolBarManager, categoryTableModel, crudHolder),
                        TabFactory.createTemplatesTab(frame, toolBarManager, templateTableModel, crudHolder),
                        TabFactory.createIntervalsTab(frame, toolBarManager, intervalTableModel, crudHolder),
                        TabFactory.createHelpTab(frame, toolBarManager)
                )
        );

        for (var tab : tabs) {
            tab.addToPane(tabbedPane);
        }
        ((JTable) tabs.get(0).getComponent()).setRowSorter(rowSorter);
    }


    private void refreshModels() {
        // TODO ImportOption should be chosen by user
        ImportOption option = ImportOption.REWRITE;
        scheduleTableModel.refreshFromCrud(option);
        categoryTableModel.refreshFromCrud(option);
        templateTableModel.refreshFromCrud(option);
        intervalTableModel.refreshFromCrud(option);
    }

    private JFrame createFrame() {
        var frame = new JFrame("TODO App");
        frame.setMinimumSize(new Dimension(750, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }
}
