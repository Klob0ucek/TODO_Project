package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteEvent extends AbstractDeleteAction {
    public DeleteEvent(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
        putValue(SHORT_DESCRIPTION, "Delete selected event/events (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
