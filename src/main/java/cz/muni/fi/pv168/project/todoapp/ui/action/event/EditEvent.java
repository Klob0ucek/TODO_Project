package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class EditEvent extends AbstractEditAction {
    public EditEvent(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
        putValue(SHORT_DESCRIPTION, "Edits selected event");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
