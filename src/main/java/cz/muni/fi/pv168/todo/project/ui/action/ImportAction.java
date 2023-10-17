package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.ui.dialog.ImportDialog;
import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

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