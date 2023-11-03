package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class EditEvent extends AbstractEditAction {
    public EditEvent(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
