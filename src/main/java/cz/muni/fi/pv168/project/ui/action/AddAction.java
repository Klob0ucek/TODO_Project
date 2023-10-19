package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class AddAction extends SmartAction {
    public AddAction(
            TabHolder tabHolder
    ) {
        super("Add", null, ActionType.ADD, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}
