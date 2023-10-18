package cz.muni.fi.pv168.project.todoapp.ui.action;


import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class EditAction extends AbstractAction {
    private final ActionType actionType = ActionType.EDIT;
    private final TabHolder tabHolder;

    public EditAction(TabHolder tabHolder) {
        super("Edit", null); // TODO: add *icon*
        this.tabHolder = tabHolder;
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }

    public ActionType getActionType() {
        return actionType;
    }

    public TabHolder getTabHolder() {
        return tabHolder;
    }
}
