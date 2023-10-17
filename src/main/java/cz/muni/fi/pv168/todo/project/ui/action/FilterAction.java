package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class FilterAction extends SmartAction {
    public FilterAction(
            TabHolder tabHolder
    ) {
        super("Filter", null, ActionType.FILTER, tabHolder);  // TODO: add *icon*
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}