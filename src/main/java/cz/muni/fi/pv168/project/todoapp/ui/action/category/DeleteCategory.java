package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.event.KeyEvent;

public class DeleteCategory extends AbstractDeleteAction {
    public DeleteCategory(
            JTable table,
            JFrame frame
    ) {
        super(Icons.DELETE.getIcon(), table, frame);
        putValue(SHORT_DESCRIPTION, "Delete selected category/categories (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }
}
