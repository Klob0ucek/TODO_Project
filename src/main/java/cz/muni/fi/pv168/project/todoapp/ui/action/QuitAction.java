package cz.muni.fi.pv168.project.todoapp.ui.action;

import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class QuitAction extends AbstractAction {
    public QuitAction() {
        super("Quit", Icons.EXIT_ICON);  // TODO: add *icon*
        putValue(SHORT_DESCRIPTION, "Quits TODOapp");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        System.exit(0);
    }
}
