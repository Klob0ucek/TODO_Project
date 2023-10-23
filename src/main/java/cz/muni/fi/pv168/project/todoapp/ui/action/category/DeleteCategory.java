package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class DeleteCategory extends AbstractDeleteAction {
    public DeleteCategory(
            TabHolder tabHolder,
            JTable table
    ) {
        super(null, tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
