package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class ImportAction extends SmartAction {
    public ImportAction(
            TabHolder tabHolder
    ) {
        super("Import", null, ActionType.IMPORT, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}