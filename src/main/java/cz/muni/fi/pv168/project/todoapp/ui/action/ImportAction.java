package cz.muni.fi.pv168.project.todoapp.ui.action;

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

        SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                try {
                    importService.importData(importPath, importOption.get());
                    new NotificationDialog(frame, "File imported successfully").showNotification();
                    filter.resetFilters();
                    refreshModels.run();
                } catch (DataManipulationException exception) {
                    new NotificationDialog(frame, "Broken file, import failed: " + exception.getMessage()).showNotification();
                }
                return null;
            }
        };

        swingWorker.execute();
    }
}
