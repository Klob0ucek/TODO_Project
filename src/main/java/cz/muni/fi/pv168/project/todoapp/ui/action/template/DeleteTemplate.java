package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteTemplate extends AbstractDeleteAction {
    public DeleteTemplate(
            JTable table
    ) {
        super(tabHolder, table);
        putValue(SHORT_DESCRIPTION, "Delete selected template/templates (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
