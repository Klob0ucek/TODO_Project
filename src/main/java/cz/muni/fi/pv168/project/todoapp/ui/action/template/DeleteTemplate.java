package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteTemplate extends AbstractDeleteAction {
    public DeleteTemplate(
            JTable table,
            JFrame frame
    ) {
        super(Icons.DELETE.getIcon(), table, frame);
        putValue(SHORT_DESCRIPTION, "Delete selected template/templates (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
