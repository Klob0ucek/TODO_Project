package cz.muni.fi.pv168.todo.project.ui.action;

import cz.muni.fi.pv168.todo.project.ui.tab.TabHolder;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {
    private final ActionType actionType = ActionType.DELETE;
    private final TabHolder tabHolder;

    public DeleteAction(TabHolder tabHolder) {
        super("Delete", null); // TODO: add *icon*
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
