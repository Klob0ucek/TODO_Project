package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.AddInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.DeleteInterval;
import cz.muni.fi.pv168.project.todoapp.ui.action.interval.EditInterval;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JTable;

import java.awt.Component;

public class IntervalsTab extends GeneralTab {
    private final Action add = new AddInterval(tabHolder, (JTable) component);
    private final Action edit = new EditInterval(tabHolder, (JTable) component);
    private final Action delete = new DeleteInterval(tabHolder, (JTable) component);

    public IntervalsTab(
            String title,
            Icon icon,
            Component component,
            String tip,
            ToolBarManager toolBarHolder,
            TabHolder tabHolder
    ) {
        super(title, icon, component, tip, toolBarHolder, tabHolder);
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
