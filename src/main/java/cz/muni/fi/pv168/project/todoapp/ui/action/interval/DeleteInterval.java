package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class DeleteInterval extends AbstractDeleteAction {
    public DeleteInterval(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
