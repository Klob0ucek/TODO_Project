package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractDeleteAction extends AbstractAction {
    protected final TabHolder tabHolder;

    public AbstractDeleteAction(
            Icon icon,
            TabHolder tabHolder
    ) {
        super("Delete", icon);
        this.tabHolder = tabHolder;
    }
}
