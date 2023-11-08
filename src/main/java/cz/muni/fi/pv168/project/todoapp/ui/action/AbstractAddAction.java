package cz.muni.fi.pv168.project.todoapp.ui.action;


import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractAddAction extends AbstractAction {
    private final JTable table;

    protected final JFrame frame;

    public JTable getTable() {
        return table;
    }

    public AbstractAddAction(
            Icon icon,
            JTable table,
            JFrame frame
    ) {
        super("Add", icon);
        this.table = table;
        this.frame = frame;
    }
}
