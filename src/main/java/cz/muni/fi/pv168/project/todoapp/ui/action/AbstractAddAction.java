package cz.muni.fi.pv168.project.todoapp.ui.action;


import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractAddAction extends AbstractAction {
    private final JTable table;
    private final JFrame frame;
    private final CrudHolder crudHolder;

    public JTable getTable() {
        return table;
    }

    public AbstractAddAction(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super("Add", Icons.ADD.getIcon());
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
