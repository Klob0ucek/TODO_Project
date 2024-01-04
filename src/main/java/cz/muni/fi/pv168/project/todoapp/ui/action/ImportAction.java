package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.error.ValidationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.DataManipulationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.ImportService;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportExportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportOptionDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutionException;

public class ImportAction extends AbstractAction {
    private final ImportService importService;
    private final JFrame frame;
    private final Filter filter;
    private final Runnable refreshModels;

    public ImportAction(JFrame frame, ImportService importService, Filter filter, Runnable refreshModels) {
        super("Import", Icons.IMPORT.getIcon());
        this.frame = frame;
        putValue(SHORT_DESCRIPTION, "Import data from a file (Alt + i)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
        this.importService = importService;
        this.filter = filter;
        this.refreshModels = refreshModels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var importDialog = new ImportExportDialog(importService.getFormats());
        String importPath = importDialog.showFileChooserDialog(frame);
        if (importPath == null) return;

        var importOptionDialog = new ImportOptionDialog(importPath);
        var importOption = importOptionDialog.show(frame, "Import data");
        if (importOption.isEmpty()) return;

        SwingWorker<NotificationDialog, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected NotificationDialog doInBackground() {
                try {
                    importService.importData(importPath, importOption.get());
                    filter.resetFilters();
                    refreshModels.run();
                    return new NotificationDialog(
                            frame, "File imported successfully!"
                    );
                } catch (DataManipulationException exception) {
                    return new NotificationDialog(
                            frame, "Import failed - broken file: " + exception.getMessage()
                    );
                } catch (ValidationException validationException) {
                    return new NotificationDialog(
                            frame, "Invalid object cannot be imported!", validationException.getValidationErrors()
                    );
                }
            }

            @Override
            protected void done() {
                try {
                    get().showNotification();
                } catch (InterruptedException | ExecutionException e) {
                    new NotificationDialog(frame, "Import failed - an error occurred: " + e.getMessage());
                }
            }
        };

        swingWorker.execute();
    }
}
