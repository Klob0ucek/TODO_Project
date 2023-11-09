package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;

import javax.swing.JTable;

public class DeleteEvent extends AbstractDeleteAction {
    public DeleteEvent(
            JTable table
    ) {
        super(null, table);
    }
}
