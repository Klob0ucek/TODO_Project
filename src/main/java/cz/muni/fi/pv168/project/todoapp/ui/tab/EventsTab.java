package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.AddEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.DeleteEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.EditEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JTable;

import java.awt.Component;

public class EventsTab extends GeneralTab {
    private final Action add = new AddEvent((JTable) component);
    private final Action edit = new EditEvent((JTable) component);
    private final Action delete = new DeleteEvent((JTable) component);

    public EventsTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarManager
    ) {
        super(title, icon, component, tip, toolBarManager);
    }

    @Override
    public void updateToolBar() {
        this.getToolBarManager()
                .reset()
                .addAction(ToolBarManager.ModifyAction.ADD, add)
                .addAction(ToolBarManager.ModifyAction.EDIT, edit)
                .addAction(ToolBarManager.ModifyAction.DELETE, delete)
                .saveChanges();
    }
}
