package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.JTable;

public abstract class AbstractAddAction extends AbstractAction {
    private final JTable table;

    public JTable getTable() {
        return table;
    }

    public AbstractAddAction(
            TabHolder tabHolder,
            JTable table
    ) {
        super("Add", Icons.ADD.getIcon());
        this.tabHolder = tabHolder;
        this.table = table;
    }
}
