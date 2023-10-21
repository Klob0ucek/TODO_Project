package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractEditAction extends AbstractAction {
    protected final TabHolder tabHolder;

    public AbstractEditAction(
            Icon icon,
            TabHolder tabHolder
    ) {
        super("Edit", icon);
        this.tabHolder = tabHolder;
    }
}
