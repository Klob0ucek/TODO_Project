package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.tab.TabHolder;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractAction {
    private TabHolder tabHolder;

    public DeleteAction(
            TabHolder tabHolder
    ) {
        super("Delete", null);  // TODO: add *icon*
        this.tabHolder = tabHolder;
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        // TODO: it should really do something, shouldn't it?
    }
}
