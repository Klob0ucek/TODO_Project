package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.MainWindow;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;

import javax.swing.JFrame;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportAction extends AbstractAction {

    private final JFrame frame;

    public ExportAction(JFrame frame) {
        super("Export", Icons.EXPORT.getIcon());
        this.frame = frame;
        putValue(SHORT_DESCRIPTION, "Export selected data (Alt + o)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO we will probably need a file a file to save here
        var dialog = new ExportDialog();
        dialog.selectExportFolder();

        NotificationDialog notificationDialog = new NotificationDialog(frame, "Successfully exported to selected folder.");
        notificationDialog.showNotification();
        // TODO confirm export success
    }
}
