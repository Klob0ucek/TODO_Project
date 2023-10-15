package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.ui.tab.TabHolder;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class SmartAction extends AbstractAction {
    private final ActionType actionType;
    private final TabHolder tabHolder;

    public SmartAction(
            String name,
            Icon icon,
            ActionType actionType,
            TabHolder tabHolder
    ) {
        super(name, icon);
        this.actionType = actionType;
        this.tabHolder = tabHolder;
    }

    public ActionType getActionType() {
        return actionType;
    }

    protected TabHolder getTabHolder() {
        return tabHolder;
    }
}
