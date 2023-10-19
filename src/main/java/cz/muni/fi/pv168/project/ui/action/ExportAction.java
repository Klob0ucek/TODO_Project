package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.dialog.ExportDialog;
import cz.muni.fi.pv168.project.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class ExportAction extends SmartAction {
    public ExportAction(TabHolder tabHolder) {
        super("Export", null, ActionType.EXPORT, tabHolder);
        // TODO: add *icon*
        putValue(SHORT_DESCRIPTION, "Exports copy of current workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO we will probably need a file a file to save here
        var dialog = new ExportDialog();
        // TODO confirm export success
    }
}