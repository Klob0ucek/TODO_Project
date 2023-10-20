package cz.muni.fi.pv168.project.todoapp.ui.tab;

import cz.muni.fi.pv168.project.todoapp.ui.ToolBarManager;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.AddTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.DeleteTemplate;
import cz.muni.fi.pv168.project.todoapp.ui.action.template.EditTemplate;

import javax.swing.Action;
import javax.swing.Icon;

import java.awt.Component;

public class TemplatesTab extends GeneralTab {
    private final Action add = new AddTemplate();
    private final Action edit = new EditTemplate();
    private final Action delete = new DeleteTemplate();

    public TemplatesTab(
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
                .addAction(ToolBarManager.ModifyAction.ADD, add)
                .addAction(ToolBarManager.ModifyAction.EDIT, edit)
                .addAction(ToolBarManager.ModifyAction.DELETE, delete)
                .saveChanges();
    }
}
