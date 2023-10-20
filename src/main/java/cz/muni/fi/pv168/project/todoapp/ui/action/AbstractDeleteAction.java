package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractDeleteAction extends AbstractAction {
    public AbstractDeleteAction(
            Icon icon
    ) {
        super("Delete", icon);
    }
}
