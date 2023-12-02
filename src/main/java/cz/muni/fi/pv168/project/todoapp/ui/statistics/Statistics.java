package cz.muni.fi.pv168.project.todoapp.ui.statistics;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Statistics {
    private final CrudHolder crudHolder;
    private final JPanel statsPanel;
    private final JButton toggleButton;

    public Statistics(CrudHolder crudHolder) {
        this.crudHolder = crudHolder;
        this.statsPanel = createStatsPanel();
        this.toggleButton = createToggleButton();
    }

    public JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        new BoxLayout(panel, BoxLayout.X_AXIS);
        fillData(panel);
        panel.setVisible(false);
        return panel;
    }

    private void fillData(JPanel panel) {
        panel.add(getLabel("Planned events: ", crudHolder.getPlannedEventsCount()));
        panel.add(getLabel("Finished events: ", crudHolder.getDoneEventsCount()));
        panel.add(getLabel("Events total: ", crudHolder.getEvents().size()));
        panel.add(getLabel("Categories: ", crudHolder.getCategories().size()));
        panel.add(getLabel("Templates: ", crudHolder.getTemplates().size()));
        panel.add(getLabel("Intervals: ", crudHolder.getIntervals().size()));
    }

    private void refreshData() {
        statsPanel.removeAll();
        fillData(statsPanel);
    }

    private JLabel getLabel(String name, long count) {
        return new JLabel("  " + name + count + "  ");
    }

    private JButton createToggleButton() {
        JButton button = new JButton(Icons.UP.getSmallIcon());
        button.setToolTipText("Show statistics");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!statsPanel.isVisible()) {
                    refreshData();
                    toggleButton.setIcon(Icons.DOWN.getSmallIcon());
                    toggleButton.setToolTipText("Hide statistics + refresh");
                } else {
                    toggleButton.setToolTipText("Show statistics");
                    toggleButton.setIcon(Icons.UP.getSmallIcon());
                }
                statsPanel.setVisible(!statsPanel.isVisible());
            }
        });
        return button;
    }

    public Component getStats() {
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(toggleButton, BorderLayout.NORTH);
        containerPanel.add(statsPanel, BorderLayout.CENTER);
        return containerPanel;
    }
}
