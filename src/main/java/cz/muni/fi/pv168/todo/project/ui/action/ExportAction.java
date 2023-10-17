package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class ExportAction extends AbstractAction {
    private final ActionType actionType = ActionType.EXPORT;
    private final TabHolder tabHolder;

    public ExportAction(TabHolder tabHolder) {
        super("Export", null); // TODO: add *icon*
        this.tabHolder = tabHolder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO we will probably need a file a file to save here
        var dialog = new ExportDialog();
        // TODO confirm export success
    }

    public ActionType getActionType() {
        return actionType;
    }

    public TabHolder getTabHolder() {
        return tabHolder;
    }
}