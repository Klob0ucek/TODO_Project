package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarHolder;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Component;

public abstract class GeneralTab {
    private final String title;
    private final String tip;
    private final Icon icon;
    private final Component component;
    private final ToolBarHolder toolBarHolder;

    public Component getComponent() {
        return component;
    }

    public ToolBarHolder getToolBarHolder() {
        return toolBarHolder;
    }

    public GeneralTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarHolder toolBarHolder
    ) {
        this.title = title;
        this.tip = tip;
        this.icon = icon;
        this.component = component;
        this.toolBarHolder = toolBarHolder;
    }

    // TODO: add javadoc
    public abstract void updateToolBar();

    public void addToPane(
            JTabbedPane tabbedPane
    ) {
        tabbedPane.addTab(title, icon, new JScrollPane(component), tip);
    }
}
