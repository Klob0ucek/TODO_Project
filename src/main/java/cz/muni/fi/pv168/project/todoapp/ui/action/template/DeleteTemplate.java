package cz.muni.fi.pv168.project.todoapp.ui.action.template;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.action.AbstractDeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.event.KeyEvent;

public class DeleteTemplate extends AbstractDeleteAction {
    public DeleteTemplate(
            JTable table,
            JFrame frame,
            CrudHolder crudHolder
    ) {
        super(table, frame, crudHolder);
        putValue(SHORT_DESCRIPTION, "Delete selected template/templates (Alt + d)");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }
}
