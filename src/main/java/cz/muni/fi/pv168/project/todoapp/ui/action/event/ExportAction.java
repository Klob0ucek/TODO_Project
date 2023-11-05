package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ExportAction extends AbstractAction {
    public ExportAction() {
        super("Export", null);
        // TODO: add *icon*
        // putValue(SHORT_DESCRIPTION, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO we will probably need a file a file to save here
        var dialog = new ExportDialog();
        dialog.selectExportFolder();

        NotificationDialog notificationDialog = new NotificationDialog(MainWindow.getFrame(), "Successfully exported to selected folder.");
        notificationDialog.showNotification();
        // TODO confirm export success
    }
}
