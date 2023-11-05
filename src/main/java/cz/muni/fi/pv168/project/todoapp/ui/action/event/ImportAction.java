package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ImportAction extends AbstractAction {
    public ImportAction() {
        super("Import", null);  // TODO: add *icon*
        // putValue(SHORT_DESCRIPTION, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ImportDialog();
        dialog.selectImportFile();

        // TODO Import result popup message
        NotificationDialog notificationDialog = new NotificationDialog(MainWindow.getFrame(), "Importing file Successful");
        notificationDialog.showNotification();

    }
}