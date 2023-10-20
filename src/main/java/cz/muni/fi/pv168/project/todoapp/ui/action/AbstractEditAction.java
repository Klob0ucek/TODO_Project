package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractEditAction extends AbstractAction {
    public AbstractEditAction(
            Icon icon
    ) {
        super("Edit", icon);
    }
}
