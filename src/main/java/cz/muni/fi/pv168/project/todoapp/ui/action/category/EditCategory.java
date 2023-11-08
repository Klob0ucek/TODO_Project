package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class EditCategory extends AbstractEditAction {
    public EditCategory(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
