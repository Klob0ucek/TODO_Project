package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddIntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class AddInterval extends AbstractAddAction {
    public AddInterval(
            JTable table
    ) {
        super(null, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var intervalTableModel = (IntervalTableModel) this.getTable().getModel();
        var dialog = new AddIntervalDialog();
        dialog.show(this.getTable(), "Add interval").ifPresent(intervalTableModel::addRow);
    }
}
