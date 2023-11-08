package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

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
