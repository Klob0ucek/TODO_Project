package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;

import javax.swing.Icon;

import java.awt.Component;

public class HelpTab extends GeneralTab {
    public HelpTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarHolder
    ) {
        super(title, icon, component, tip, toolBarHolder);
    }

    @Override
    public void updateToolBar() {
        this.getToolBarManager()
                .reset()
                .saveChanges();
    }
}
