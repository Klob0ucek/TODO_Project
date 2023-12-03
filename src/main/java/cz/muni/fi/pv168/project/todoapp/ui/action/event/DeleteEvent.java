package cz.muni.fi.pv168.project.todoapp.ui.action.event;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteEvent extends AbstractDeleteAction {
    private final Filter filter;

    public DeleteEvent(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder,
            Filter filter
    ) {
        super(Icons.DELETE.getIcon(), table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Delete selected event/events (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        this.filter = filter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        filter.resetFilters();
    }
}
