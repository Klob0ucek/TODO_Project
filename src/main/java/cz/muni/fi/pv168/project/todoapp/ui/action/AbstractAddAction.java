package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

public abstract class AbstractAddAction extends AbstractAction {
    protected final TabHolder tabHolder;
    protected final JTable table;

    public AbstractAddAction(
            Icon icon,
            TabHolder tabHolder,
            JTable table
    ) {
        super("Add", icon);
        this.tabHolder = tabHolder;
        this.table = table;
    }
}
