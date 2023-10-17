package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class DeleteAction extends SmartAction {
    public DeleteAction(
            TabHolder tabHolder
    ) {
        super("Delete", null, ActionType.DELETE, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}
