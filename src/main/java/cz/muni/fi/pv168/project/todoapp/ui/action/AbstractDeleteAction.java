package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractDeleteAction extends AbstractAction {
    private final JTable table;
    private final JFrame frame;

    public AbstractDeleteAction(
            Icon icon,
            JTable table,
            JFrame frame
    ) {
        super("Delete", icon);
        this.table = table;
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }
}
