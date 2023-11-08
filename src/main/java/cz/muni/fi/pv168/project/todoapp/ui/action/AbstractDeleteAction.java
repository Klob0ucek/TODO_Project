package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

public abstract class AbstractDeleteAction extends AbstractAction {
    protected final TabHolder tabHolder;
    protected final JTable table;

    public AbstractDeleteAction(
            TabHolder tabHolder,
            JTable table
    ) {
        super("Delete", Icons.DELETE.getIcon());
        this.tabHolder = tabHolder;
        this.table = table;
    }
}
