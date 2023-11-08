package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

public abstract class AbstractEditAction extends AbstractAction {
    protected final JTable table;

    public AbstractEditAction(
            Icon icon,
            JTable table
    ) {
        super("Edit", icon);
        this.table = table;
    }
}
