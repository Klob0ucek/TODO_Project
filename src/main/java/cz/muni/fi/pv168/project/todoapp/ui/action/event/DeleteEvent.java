package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class DeleteEvent extends AbstractDeleteAction {
    public DeleteEvent(
            TabHolder tabHolder,
            JTable table
    ) {
        super(null, tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
