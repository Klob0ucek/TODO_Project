package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddIntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class AddInterval extends AbstractAddAction {
    public AddInterval(
            TabHolder tabHolder,
            JTable table
    ) {
        super(tabHolder, table);
        putValue(SHORT_DESCRIPTION, "Adds new interval");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var intervalTableModel = (IntervalTableModel) table.getModel();

        var dialog = new AddIntervalDialog();
        dialog.show(table, "Add interval").ifPresent(intervalTableModel::addRow);
    }
}
