package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractEditAction extends AbstractAction {
    protected final JTable table;
    protected final JFrame frame;

    public AbstractEditAction(
            Icon icon,
            JTable table,
            JFrame frame
    ) {
        super("Edit", icon);
        this.table = table;
        this.frame = frame;
    }
}
