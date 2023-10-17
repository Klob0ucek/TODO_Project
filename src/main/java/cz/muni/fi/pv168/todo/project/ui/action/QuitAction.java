package cz.muni.fi.pv168.todo.project.ui.action;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class QuitAction extends AbstractAction {
    public QuitAction() {
        super("Quit", null);  // TODO: add *icon*
        putValue(SHORT_DESCRIPTION, "Terminate the application");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {
        System.exit(0);
    }
}
