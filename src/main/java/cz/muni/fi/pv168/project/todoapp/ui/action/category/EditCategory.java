package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditCategory extends AbstractEditAction {
    public EditCategory(
            JTable table,
            JFrame frame
    ) {
        super(Icons.EDIT.getIcon(), table, frame);
        putValue(SHORT_DESCRIPTION, "Edit selected category (Alt + e)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
