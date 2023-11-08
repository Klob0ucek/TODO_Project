package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;

public abstract class AbstractDeleteAction extends AbstractAction {
    protected final JTable table;

    protected final JFrame frame;

    public AbstractDeleteAction(
            Icon icon,
            JTable table,
            JFrame frame
    ) {
        super("Delete", icon);
        this.table = table;
        this.frame = frame;
    }
}
