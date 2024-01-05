package cz.muni.fi.pv168.project.todoapp.business.service.export;


import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.BatchOperationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;

import java.util.Collection;

/**
 * Generic mechanism, allowing to import data from a file.
 */
public interface ImportService {

    /**
     * Imports data from a file.
     *
     * @param filePath     absolute path of the export file (to be created or overwritten)
     * @param importOption option to import data (merge or rewrite)
     * @throws BatchOperationException if the import cannot be done
     */
    void importData(String filePath, ImportOption importOption);

    /**
     * Gets all available formats for import.
     */
    Collection<Format> getFormats();
}
