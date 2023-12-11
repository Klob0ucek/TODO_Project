package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Icon;

import java.awt.event.ActionEvent;

public abstract class AbstractEditAction extends AbstractAction {
    private final JTable table;
    private final JFrame frame;
    private final CrudHolder crudHolder;

    public AbstractEditAction(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super("Edit", Icons.EDIT.getIcon());
        this.table = table;
        this.frame = frame;
        this.crudHolder = crudHolder;
    }

    public void checkSelectedCountAndCancelEditing() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (table.isEditing()) {
            table.getCellEditor().cancelCellEditing();
        }
    }

    public int getSelectedRowModelIndex() {
        return table.convertRowIndexToModel(table.getSelectedRows()[0]);
    }

    public JTable getTable() {
        return table;
    }

    public JFrame getFrame() {
        return frame;
    }

    public CrudHolder getCrudHolder() {
        return crudHolder;
    }
}
