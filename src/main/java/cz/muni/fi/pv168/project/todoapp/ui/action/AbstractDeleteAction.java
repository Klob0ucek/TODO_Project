package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.model.BasicTableModel;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Comparator;

public abstract class AbstractDeleteAction extends AbstractAction {
    private final JTable table;
    private final JFrame frame;
    private final CrudHolder crudHolder;

    public AbstractDeleteAction(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super("Delete", Icons.DELETE.getIcon());
        this.table = table;
        this.frame = frame;
        this.crudHolder = crudHolder;
    }

    public JFrame getFrame() {
        return frame;
    }

    protected boolean isUserApproved() {
        JOptionPane pane = new JOptionPane();
        pane.setMessage("You have " + this.table.getSelectedRowCount() + " rows selected for deletion.");
        pane.setMessageType(JOptionPane.WARNING_MESSAGE);
        pane.setOptionType(JOptionPane.YES_NO_OPTION);

        var dialog = pane.createDialog(this.frame, "Are you sure?");
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
        Arrays.stream(this.table.getSelectedRows())
                // view row index must be converted to model row index
                .map(this.table::convertRowIndexToModel)
                .boxed()
                // We need to delete rows in descending order to not change index of rows
                // which are not deleted yet
                .sorted(Comparator.reverseOrder())
                .forEach(tableModel::deleteRow);
    }
}
