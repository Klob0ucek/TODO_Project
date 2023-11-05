package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;
<<<<<<< Updated upstream
=======
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

>>>>>>> Stashed changes
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ImportAction extends AbstractAction {
    public ImportAction() {
        super("Import", Icons.IMPORT_ICON);  // TODO: add *icon*
        // putValue(SHORT_DESCRIPTION, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ImportDialog();
        // TODO Import result popup message
    }
}