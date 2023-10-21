package cz.muni.fi.pv168.project.todoapp.ui.action.category;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import java.awt.event.ActionEvent;

public class DeleteCategory extends AbstractDeleteAction {
    public DeleteCategory(
            TabHolder tabHolder
    ) {
        super(null, tabHolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
