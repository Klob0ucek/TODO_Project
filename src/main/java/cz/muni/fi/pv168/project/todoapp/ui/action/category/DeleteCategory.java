package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class DeleteCategory extends AbstractDeleteAction {
    public DeleteCategory(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
