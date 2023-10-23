package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractEditAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class EditTemplate extends AbstractEditAction {
    public EditTemplate(
            TabHolder tabHolder,
            JTable table
    ) {
        super(null, tabHolder, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
