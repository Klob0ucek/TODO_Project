package cz.muni.fi.pv168.todo.project.ui.tab;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabChangeListener implements ChangeListener {
    private final TabHolder tabHolder;

    public TabChangeListener(
            TabHolder tabHolder
    ) {
        this.tabHolder = tabHolder;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        tabHolder.getCurrentTab().updateActions();
    }
}
