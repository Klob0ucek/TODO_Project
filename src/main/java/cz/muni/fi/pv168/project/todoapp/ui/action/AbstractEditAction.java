package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractEditAction extends AbstractAction {
    private final JTable table;
    private final JFrame frame;
    private final CrudHolder crudHolder;

    public AbstractEditAction(
            Icon icon,
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super("Edit", icon);
        this.table = table;
        this.frame = frame;
        this.crudHolder = crudHolder;
    }

    public JFrame getFrame() {
        return frame;
    }
    public CrudHolder getCrudHolder() {
        return crudHolder;
    }
}
