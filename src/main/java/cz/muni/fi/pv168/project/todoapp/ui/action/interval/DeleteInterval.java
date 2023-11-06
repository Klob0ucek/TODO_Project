package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class DeleteInterval extends AbstractDeleteAction {
    public DeleteInterval(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
        putValue(SHORT_DESCRIPTION, "Deletes selected interval/intervals");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
