package cz.muni.fi.pv168.project.todoapp.ui.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractAddAction extends AbstractAction {
    public AbstractAddAction(
            Icon icon
    ) {
        super("Add", icon);
    }
}
