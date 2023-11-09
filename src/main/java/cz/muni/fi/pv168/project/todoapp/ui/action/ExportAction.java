package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.export.ExportService;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ExportDialog;
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
        putValue(SHORT_DESCRIPTION, "Export selected data (Alt + o)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
        this.exportService = exportService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var exportDialog = new ExportDialog(exportService.getFormats());
        String exportPath = exportDialog.showFileChooserDialog();
        if (exportPath == null) {
            return;
        }
        if (!exportDialog.showConfirmationDialog(exportPath)) {
            return;
        }
        NotificationDialog notificationDialog = new NotificationDialog(frame, "Successfully exported to selected folder.");
        notificationDialog.showNotification();
        exportService.exportData(exportPath);

    }
}
