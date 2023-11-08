package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.Icon;

public abstract class AbstractAddAction extends AbstractAction {
    private final JTable table;

    public JTable getTable() {
        return table;
    }

    public AbstractAddAction(
            Icon icon,
            JTable table
    ) {
        super("Add", icon);
        this.table = table;
    }
}
