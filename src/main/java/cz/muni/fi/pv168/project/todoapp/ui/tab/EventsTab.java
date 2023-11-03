package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.AddEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.DeleteEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.EditEvent;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ImportAction;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JTable;

import java.awt.Component;

public class EventsTab extends GeneralTab {
    private final Action add = new AddEvent(tabHolder, (JTable) component);
    private final Action edit = new EditEvent(tabHolder, (JTable) component);
    private final Action delete = new DeleteEvent(tabHolder, (JTable) component);
    private final Action importer = new ImportAction();
    private final Action exporter = new ExportAction();

    public EventsTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarManager,
            TabHolder tabHolder
    ) {
        super(title, icon, component, tip, toolBarManager, tabHolder);
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
