package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteInterval extends AbstractDeleteAction {
    public DeleteInterval(
            JTable table
    ) {
        super(Icons.DELETE.getIcon(), table);
        putValue(SHORT_DESCRIPTION, "Delete selected interval/intervals (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
