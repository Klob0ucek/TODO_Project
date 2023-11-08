package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ImportAction extends AbstractAction {
    public ImportAction() {
        super("Import", Icons.IMPORT.getIcon());
        putValue(SHORT_DESCRIPTION, "Import data from a file (Alt + i)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_I);
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