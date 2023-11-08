package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditTemplate extends AbstractEditAction {
    public EditTemplate(
            JTable table
    ) {
        super(Icons.EDIT.getIcon(), table);
        putValue(SHORT_DESCRIPTION, "Edit selected template (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
