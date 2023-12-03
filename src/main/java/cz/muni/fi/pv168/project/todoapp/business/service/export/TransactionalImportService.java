package cz.muni.fi.pv168.project.todoapp.business.service.export;

import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.TransactionExecutor;

import java.util.Collection;

public class TransactionalImportService implements ImportService {
    private final ImportService importService;
    private final TransactionExecutor transactionExecutor;

    public TransactionalImportService(ImportService importService, TransactionExecutor transactionExecutor) {
        this.importService = importService;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public void importData(String filePath, ImportOption importOption) {
        transactionExecutor.executeInTransaction(() -> importService.importData(filePath, importOption));
    }

    @Override
    public Collection<Format> getFormats() {
        return importService.getFormats();
    }
}
