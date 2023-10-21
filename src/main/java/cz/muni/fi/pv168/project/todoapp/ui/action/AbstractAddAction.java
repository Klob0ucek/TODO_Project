package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class AbstractAddAction extends AbstractAction {
    protected final TabHolder tabHolder;

    public AbstractAddAction(
            Icon icon,
            TabHolder tabHolder
    ) {
        super("Add", icon);
        this.tabHolder = tabHolder;
    }
}
