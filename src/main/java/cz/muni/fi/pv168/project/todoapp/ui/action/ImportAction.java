package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.export.DataManipulationException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.ImportService;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ImportAction extends AbstractAction {
    private final ImportService importService;
    private final JFrame frame;

    private final Runnable refreshModels;

    public ImportAction(JFrame frame, ImportService importService, Runnable callback) {
        super("Import", Icons.IMPORT.getIcon());
        this.frame = frame;
        putValue(SHORT_DESCRIPTION, "Import data from a file (Alt + i)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
        this.importService = importService;

        refreshModels = callback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var importDialog = new ImportDialog(importService.getFormats());
        String importPath = importDialog.showFileChooserDialog();
        if (importPath == null) {
            return;
        }
        if (!importDialog.showConfirmationDialog(importPath)) {
            return;
        }
        // TODO if there are any data in app, should we merge or rewrite


        try {
            importService.importData(importPath);
        } catch (DataManipulationException exception) {
            NotificationDialog notificationDialog = new NotificationDialog(frame, "Broken file, import failed!");
            notificationDialog.showNotification();
            return;
        }
        NotificationDialog notificationDialog = new NotificationDialog(frame, "File imported successfully");
        notificationDialog.showNotification();
        refreshModels.run();
    }
}