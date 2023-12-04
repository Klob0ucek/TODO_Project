package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.export.ExportService;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportExportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportAction extends AbstractAction {
    private final ExportService exportService;
    private final JFrame frame;

    public ExportAction(JFrame frame, ExportService exportService) {
        super("Export", Icons.EXPORT.getIcon());
        this.frame = frame;
        putValue(SHORT_DESCRIPTION, "Export data (Alt + o)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
        this.exportService = exportService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var exportDialog = new ImportExportDialog(exportService.getFormats());
        String exportPath = exportDialog.showFileChooserDialog(frame);
        if (exportPath == null || !exportDialog.showConfirmationDialog(exportPath, frame)) return;

        SwingWorker<Object, Object> swingWorker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                exportService.exportData(exportPath);
                new NotificationDialog(frame, "Successfully exported to selected file.").showNotification();
                return null;
            }
        };

        swingWorker.execute();
    }
}
