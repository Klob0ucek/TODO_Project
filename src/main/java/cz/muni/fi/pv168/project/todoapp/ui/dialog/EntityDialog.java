package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.Optional;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

abstract class EntityDialog<E> {
    private final JPanel panel = new JPanel();

    EntityDialog() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    }

    void add(String labelText, JComponent component) {
        panel.add(new JLabel(labelText));
        panel.add(component);
    }

    public JPanel addTwoComponentPanel(String firstLabelText, JComponent firstComponent,
                                       String secondLabelText, JComponent secondComponent) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.X_AXIS));
        newPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        newPanel.add(new JLabel(firstLabelText + " "));
        newPanel.add(firstComponent);
        newPanel.add(Box.createHorizontalStrut(10));
        newPanel.add(new JLabel(secondLabelText + " "));
        newPanel.add(secondComponent);
        panel.add(newPanel);
        return newPanel;
    }

    abstract E getEntity();

    public Optional<E> show(Component parentComponent, String title) {
        int result = JOptionPane.showOptionDialog(parentComponent, panel, title,
                OK_CANCEL_OPTION, PLAIN_MESSAGE, null, null, null);

        if (result == OK_OPTION) {
            return Optional.of(getEntity());
        }
        return Optional.empty();
    }
}
