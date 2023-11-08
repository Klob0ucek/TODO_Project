package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditInterval extends AbstractEditAction {
    public EditInterval(
            JTable table,
            JFrame frame
    ) {
        super(Icons.EDIT.getIcon(), table, frame);
        putValue(SHORT_DESCRIPTION, "Edit selected interval (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
