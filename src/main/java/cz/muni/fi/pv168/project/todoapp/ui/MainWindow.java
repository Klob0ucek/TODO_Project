package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.business.Repository;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.CategoryColor;
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
import cz.muni.fi.pv168.project.todoapp.business.service.export.TransactionalImportService;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.EventValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.IntervalValidator;
import cz.muni.fi.pv168.project.todoapp.business.service.validation.TemplateValidator;
import cz.muni.fi.pv168.project.todoapp.storage.sql.CategorySqlRepository;
import cz.muni.fi.pv168.project.todoapp.storage.sql.EventSqlRepository;
import cz.muni.fi.pv168.project.todoapp.storage.sql.IntervalSqlRepository;
import cz.muni.fi.pv168.project.todoapp.storage.sql.TemplateSqlRepository;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.CategoryDao;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.EventDao;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.IntervalDao;
import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.TemplateDao;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.DatabaseManager;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.TransactionConnectionSupplier;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.TransactionExecutorImpl;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.TransactionManagerImpl;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.CategoryMapper;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.EventMapper;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.IntervalMapper;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.TemplateMapper;
import cz.muni.fi.pv168.project.todoapp.ui.action.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.ImportAction;
import cz.muni.fi.pv168.project.todoapp.ui.filter.EventTableFilter;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterCategoryValues;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.ScheduleTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.model.TemplateTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.statistics.Statistics;
import cz.muni.fi.pv168.project.todoapp.ui.tab.GeneralTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabFactory;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.utils.Either;

import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private final JFrame frame = createFrame();
    private final List<GeneralTab> tabs = new ArrayList<>();
    private final DatabaseManager databaseManager = createDatabaseManager();
    private final CrudHolder crudHolder = createCrudHolder();
    private ScheduleTableModel scheduleTableModel;
    private CategoryTableModel categoryTableModel;
    private TemplateTableModel templateTableModel;
    private IntervalTableModel intervalTableModel;
    private GenericExportService exportService;
    private TransactionalImportService importService;

    public MainWindow() {
        JComponent verticalToolBar = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);
        TableRowSorter<ScheduleTableModel> rowSorter = new TableRowSorter<>(scheduleTableModel);
        rowSorter.toggleSortOrder(4);
        EventTableFilter eventTableFilter = new EventTableFilter(rowSorter, crudHolder);
        DefaultComboBoxModel<Category> comboBoxModel = new DefaultComboBoxModel<>(crudHolder.getCategories().toArray(Category[]::new));
        Filter filter = new Filter(crudHolder, eventTableFilter, comboBoxModel);
        categoryTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                Either<SpecialFilterCategoryValues, Category> selected = (Either<SpecialFilterCategoryValues, Category>) comboBoxModel.getSelectedItem();
                Category placeholder = new Category("X", CategoryColor.YELLOW);
                comboBoxModel.addElement(placeholder);

                for (int size = comboBoxModel.getSize() - 1; size >= 0; size--) {
                    Category cat = comboBoxModel.getElementAt(size);
                    if (!cat.getName().equals("X")) {
                        comboBoxModel.removeElementAt(size);
                    }
                }
                if ((selected instanceof Either<?, ?>) && selected.getLeft().isEmpty()) {
                    var aux = crudHolder.getCategoryByGuid((selected.getRight().get()).getGuid());
                    selected = aux.isPresent() ? Either.right(aux.get()) : Either.left(SpecialFilterCategoryValues.ALL);
                }
                comboBoxModel.addAll(crudHolder.getCategories());
                comboBoxModel.removeElement(placeholder);
                comboBoxModel.setSelectedItem(selected);
            }
        });

        ToolBarManager toolBarManager = createToolBarManager(verticalToolBar, filter);
        createTabs(toolBarManager, tabbedPane, filter, rowSorter);

        Statistics statistics = new Statistics(crudHolder.getEventCrudService(), crudHolder.getCategoryCrudService());
        addListeners(statistics);

        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));
        tabHolder.getCurrentTab().updateToolBar();
        frame.add(filter.getFilterBar(), BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(verticalToolBar, BorderLayout.WEST);
        frame.add(statistics.getStats(), BorderLayout.SOUTH);
        frame.pack();
    }

    private DatabaseManager createDatabaseManager() {
        DatabaseManager databaseManager = DatabaseManager.createProductionInstance();
        databaseManager.initSchema();

        return databaseManager;
    }

    private CrudHolder createCrudHolder() {
        var transactionManager = new TransactionManagerImpl(databaseManager);
        var transactionExecutor = new TransactionExecutorImpl(transactionManager::beginTransaction);
        var transactionConnectionSupplier = new TransactionConnectionSupplier(transactionManager, databaseManager);

        var categoryMapper = new CategoryMapper();
        var categoryDao = new CategoryDao(transactionConnectionSupplier);
        Repository<Category> categoryRepository = new CategorySqlRepository(categoryDao, categoryMapper);

        var eventMapper = new EventMapper();
        var eventDao = new EventDao(transactionConnectionSupplier);
        Repository<Event> eventRepository = new EventSqlRepository(eventDao, eventMapper);
//        InMemoryRepository<Event> eventRepository = new InMemoryRepository<>(ExampleData.getEvents());

        TemplateMapper templateMapper = new TemplateMapper();
        TemplateDao templateDao = new TemplateDao(transactionConnectionSupplier);
        Repository<Template> templateRepository = new TemplateSqlRepository(templateDao, templateMapper);
//        InMemoryRepository<Template> templateRepository = new InMemoryRepository<>(ExampleData.getTemplates());

        IntervalMapper intervalMapper = new IntervalMapper();
        IntervalDao intervalDao = new IntervalDao(transactionConnectionSupplier);
        Repository<Interval> intervalRepository = new IntervalSqlRepository(intervalDao, intervalMapper);
//        Repository<Interval> intervalRepository = new InMemoryRepository<>(ExampleData.getIntervals());

        var eventCrudService = new EventCrudService(eventRepository, new EventValidator());
        var categoryCrudService = new CategoryCrudService(categoryRepository, new CategoryValidator());
        var templateCrudService = new TemplateCrudService(templateRepository, new TemplateValidator());
        var intervalCrudService = new IntervalCrudService(intervalRepository, new IntervalValidator());

        exportService = new GenericExportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonExporter()));
        var genericImportService = new GenericImportService(eventCrudService, categoryCrudService,
                templateCrudService, intervalCrudService, List.of(new JsonImporter()));
        importService = new TransactionalImportService(genericImportService, transactionExecutor);

        scheduleTableModel = new ScheduleTableModel(eventCrudService);
        categoryTableModel = new CategoryTableModel(categoryCrudService);
        templateTableModel = new TemplateTableModel(templateCrudService);
        intervalTableModel = new IntervalTableModel(intervalCrudService);

        return new CrudHolder(eventCrudService, categoryCrudService, templateCrudService, intervalCrudService);
    }

    private ToolBarManager createToolBarManager(JComponent verticalToolBar, Filter filter) {
        return new ToolBarManager(
                verticalToolBar,
                new ExportAction(frame, exportService),
                new ImportAction(frame, importService, filter, this::refreshModels));
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
                        TabFactory.createCategoriesTab(frame, toolBarManager, categoryTableModel, crudHolder, this::refreshModels),
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

    private void addListeners(Statistics statistics) {
        scheduleTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                statistics.refreshData();
                System.out.println("refreshed");
                frame.setVisible(true);
            }
        });
        categoryTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                statistics.refreshData();
                System.out.println("refreshed");
                frame.setVisible(true);
            }
        });
    }


    private void refreshModels() {
        scheduleTableModel.refreshFromCrud();
        categoryTableModel.refreshFromCrud();
        templateTableModel.refreshFromCrud();
        intervalTableModel.refreshFromCrud();
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
