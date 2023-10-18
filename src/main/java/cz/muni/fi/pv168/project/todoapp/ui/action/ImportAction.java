package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.ImportDialog;


import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ImportAction extends AbstractAction {
    private final ActionType actionType = ActionType.IMPORT;
    private final TabHolder tabHolder;

    public ImportAction(TabHolder tabHolder) {
        super("Import", null); // TODO: add *icon*
        this.tabHolder = tabHolder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ImportDialog();

        // TODO Import success popup message

    }

    public ActionType getActionType() {
        return actionType;
    }

    public TabHolder getTabHolder() {
        return tabHolder;
    }
}