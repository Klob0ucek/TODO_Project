package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;
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
        // TODO Import result popup message
    }
}