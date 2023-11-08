package cz.muni.fi.pv168.project.todoapp.ui.action.interval;

import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractAddAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.AddIntervalDialog;
import cz.muni.fi.pv168.project.todoapp.ui.model.IntervalTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddInterval extends AbstractAddAction {
    public AddInterval(
            JTable table,
            JFrame frame
    ) {
        super(Icons.ADD.getIcon(), table, frame);
        putValue(SHORT_DESCRIPTION, "Add new interval (Alt + a)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var intervalTableModel = (IntervalTableModel) this.getTable().getModel();
        var dialog = new AddIntervalDialog();
        dialog.show(this.getTable(), "Add interval").ifPresent(intervalTableModel::addRow);
    }
}
