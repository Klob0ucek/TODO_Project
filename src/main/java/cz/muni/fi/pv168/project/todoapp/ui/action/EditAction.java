package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class EditAction extends SmartAction {
    public EditAction(
            TabHolder tabHolder
    ) {
        super("Edit", null, ActionType.EDIT, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}
