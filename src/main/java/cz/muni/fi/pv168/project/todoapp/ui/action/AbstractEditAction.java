package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;

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
