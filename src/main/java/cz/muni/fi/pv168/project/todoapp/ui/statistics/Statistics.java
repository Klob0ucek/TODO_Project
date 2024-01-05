package cz.muni.fi.pv168.project.todoapp.ui.statistics;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics {
    private final StatisticsProvider statisticsProvider;
    private final JPanel statsPanel = new JPanel();
    private final JButton toggleButton = createToggleButton();
    private final JButton categoryButton = createCategoryButton();
    private boolean isCategoriesDisplayed = false;

    public Statistics(CrudService<Event> eventCrudService, CrudService<Category> categoryCrudService) {
        this.statisticsProvider = new StatisticsProvider(eventCrudService, categoryCrudService);
        setupStatsPanel();
    }

    public void setupStatsPanel() {
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setPreferredSize(new Dimension(0, 40));
        statisticsProvider.fillEventData(statsPanel);
        statsPanel.setVisible(false);
        categoryButton.setVisible(false);
    }

    public void refreshData() {
        statsPanel.removeAll();
        if (isCategoriesDisplayed) {
            statisticsProvider.fillCategoriesData(statsPanel);
        } else {
            statisticsProvider.fillEventData(statsPanel);
        }

        // Methods needed to refresh statsPanel after categoriesButton is clicked
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private JButton createToggleButton() {
        JButton button = new JButton(Icons.UP.getSmallIcon());
        button.setToolTipText("Show statistics");
        button.setBorder(null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!statsPanel.isVisible()) {
                    refreshData();
                    toggleButton.setIcon(Icons.DOWN.getSmallIcon());
                    toggleButton.setToolTipText("Hide statistics + refresh");
                } else {
                    toggleButton.setIcon(Icons.UP.getSmallIcon());
                    toggleButton.setToolTipText("Show statistics");
                }
                categoryButton.setVisible(!categoryButton.isVisible());
                statsPanel.setVisible(!statsPanel.isVisible());
            }
        });
        return button;
    }

    private JButton createCategoryButton() {
        JButton button = new JButton(Icons.RIGHT.getSmallIcon());
        button.setToolTipText("Show category statistics");
        button.setBorder(null);
        button.setBackground(null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCategoriesDisplayed) {
                    button.setIcon(Icons.RIGHT.getSmallIcon());
                    isCategoriesDisplayed = false;
                    button.setToolTipText("Show category statistics");
                } else {
                    button.setIcon(Icons.LEFT.getSmallIcon());
                    isCategoriesDisplayed = true;
                    button.setToolTipText("Show event statistics");
                }
                refreshData();
            }
        });
        return button;
    }

    public Component getStats() {
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(toggleButton, BorderLayout.NORTH);
        containerPanel.add(categoryButton, BorderLayout.EAST);
        containerPanel.add(statsPanel, BorderLayout.CENTER);
        return containerPanel;
    }
}
