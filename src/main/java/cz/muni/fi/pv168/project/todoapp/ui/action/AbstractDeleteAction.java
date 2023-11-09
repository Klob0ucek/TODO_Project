package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.model.BasicTableModel;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;

public abstract class AbstractDeleteAction extends AbstractAction {
    protected final JTable table;

    public AbstractDeleteAction(
            Icon icon,
            JTable table
    ) {
        super("Delete", icon);
        this.table = table;
    }

    protected boolean isUserApproved() {
        JOptionPane pane = new JOptionPane();
        pane.setMessage("You have " + this.table.getSelectedRowCount() + " rows selected for deletion.");
        pane.setMessageType(JOptionPane.WARNING_MESSAGE);
        pane.setOptionType(JOptionPane.YES_NO_OPTION);

        var dialog = pane.createDialog(this.table, "Are you sure?");
        dialog.setVisible(true);

        Object result = pane.getValue();
        return result != null && (int) result == JOptionPane.YES_OPTION;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isUserApproved()) {
            return;
        }

        var tableModel = (BasicTableModel<?>) this.table.getModel();
        var selectedRows = this.table.getSelectedRows();

        for (var i = selectedRows.length - 1; i >= 0; i--) {
            tableModel.deleteRow(selectedRows[i]);
        }
    }
}
