package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import java.awt.event.ActionEvent;

public class PlaceholderAction extends AbstractAction {
    public PlaceholderAction(
            String name,
            Icon icon
    ) {
        super(name, icon);
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // PlaceholderAction is *always* disabled
    }
}
