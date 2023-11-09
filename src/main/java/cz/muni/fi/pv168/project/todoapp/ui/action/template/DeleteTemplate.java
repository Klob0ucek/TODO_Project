package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;

import javax.swing.JTable;

public class DeleteTemplate extends AbstractDeleteAction {
    public DeleteTemplate(
            JTable table
    ) {
        super(null, table);
    }
}
