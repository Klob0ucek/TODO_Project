package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class EditCategory extends AbstractEditAction {
    public EditCategory(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
