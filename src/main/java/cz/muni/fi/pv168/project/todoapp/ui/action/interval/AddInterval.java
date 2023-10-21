package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.model.Interval;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddIntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddInterval extends AbstractAddAction {
    public AddInterval(
            TabHolder tabHolder
    ) {
        super(null, tabHolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currentTable = (JTable) tabHolder.getCurrentTab().getComponent();
        var currentTableModel = (IntervalTableModel) currentTable.getModel();

        var dialog = new AddIntervalDialog(new Interval());
        dialog.show(currentTable, "Add interval")
                .ifPresent(currentTableModel::addRow);
    }
}
