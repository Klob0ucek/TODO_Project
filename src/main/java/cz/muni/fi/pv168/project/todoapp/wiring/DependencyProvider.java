//package cz.muni.fi.pv168.project.todoapp.wiring;
//
//import cz.muni.fi.pv168.project.todoapp.business.Repository;
//import cz.muni.fi.pv168.project.todoapp.business.model.Category;
//import cz.muni.fi.pv168.project.todoapp.business.model.Event;
//import cz.muni.fi.pv168.project.todoapp.business.service.crud.*;
//import cz.muni.fi.pv168.project.todoapp.business.service.export.*;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.CategorySqlRepository;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.EventSqlRepository;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.CategoryDao;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.dao.EventDao;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.db.*;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.CategoryMapper;
//import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.EventMapper;
//
//import java.util.List;
//
//public class DependencyProvider {
//    private final Repository<Category> categorys;
//    private final Repository<Event> events;
//    private final DatabaseManager databaseManager;
//    private final TransactionExecutor transactionExecutor;
//    private final CrudService<Event> eventCrudService;
//    private final CrudService<Category> categoryCrudService;
//    private final ImportService importService;
//    private final ExportService exportService;
//
//    public DependencyProvider(DatabaseManager databaseManager) {
//
//        this.databaseManager = databaseManager;
//        var transactionManager = new TransactionManagerImpl(databaseManager);
//        this.transactionExecutor = new TransactionExecutorImpl(transactionManager::beginTransaction);
//        var transactionConnectionSupplier = new TransactionConnectionSupplier(transactionManager, databaseManager);
//
//        var categoryMapper = new CategoryMapper();
//        var categoryDao = new CategoryDao(transactionConnectionSupplier);
//
//        var eventMapper = new EventMapper(categoryDao, categoryMapper);
//
//        this.categorys = new CategorySqlRepository(
//                categoryDao,
//                categoryMapper
//        );
//        this.events = new EventSqlRepository(
//                new EventDao(transactionConnectionSupplier),
//                eventMapper
//        );
//        categoryCrudService = new CategoryCrudService(categorys);
//        templateCrudService = new TemplateCrudService(templates);
//        intervalCrudService = new IntervalCrudService(intervals);
//        eventCrudService = new EventCrudService(events);
//        exportService = new GenericExportService(eventCrudService, categoryCrudService,
//                List.of(new JsonExporter()));
//        importService = new GenericImportService(eventCrudService, categoryCrudService,
//                List.of(new JsonImporter()));
//    }
//}
