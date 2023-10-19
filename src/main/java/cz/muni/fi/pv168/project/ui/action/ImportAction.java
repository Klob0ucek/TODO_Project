package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.project.ui.tab.TabHolder;

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