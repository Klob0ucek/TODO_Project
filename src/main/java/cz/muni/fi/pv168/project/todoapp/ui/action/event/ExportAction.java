package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.dialog.ExportDialog;
<<<<<<< Updated upstream
=======
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
>>>>>>> Stashed changes

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ExportAction extends AbstractAction {
    public ExportAction() {
        super("Export", Icons.EXPORT_ICON);
        // TODO: add *icon*
        // putValue(SHORT_DESCRIPTION, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO we will probably need a file a file to save here
        var dialog = new ExportDialog();
        dialog.selectExportFolder();
        // TODO confirm export success
    }
}
