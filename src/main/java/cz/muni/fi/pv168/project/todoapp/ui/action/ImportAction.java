package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;

import java.awt.event.ActionEvent;

public class ImportAction extends SmartAction {
    public ImportAction(TabHolder tabHolder) {
        super("Import", null, ActionType.IMPORT, tabHolder);  // TODO: add *icon*
        putValue(SHORT_DESCRIPTION, "Edits selected employee");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ImportDialog();

        // TODO Import success popup message

    }
}