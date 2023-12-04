package cz.muni.fi.pv168.project.todoapp.ui.statistics;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.resources.Icons;

import java.lang.constant.Constable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class Statistics {
    private final CrudHolder crudHolder;
    private final JPanel statsPanel;
    private final JButton toggleButton;
    private final JButton categoryButton;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private boolean isCategoriesDisplayed;

    public Statistics(CrudHolder crudHolder) {
        this.crudHolder = crudHolder;
        this.statsPanel = new JPanel();
        this.toggleButton = createToggleButton();
        this.categoryButton = createCategoryButton();
        isCategoriesDisplayed = false;
        setupStatsPanel();
    }

    public void setupStatsPanel() {
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setPreferredSize(new Dimension(0, 40));
        fillEventData();
        statsPanel.setVisible(false);
        categoryButton.setVisible(false);
    }

    private void fillEventData() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        topPanel.add(getLabel("Planned events: " + crudHolder.getPlannedEventsCount()));
        topPanel.add(getLabel("Finished events: " + crudHolder.getDoneEventsCount()));
        topPanel.add(getLabel("Events total: " + crudHolder.getEvents().size()));
        topPanel.add(getLabel("Categories: " + crudHolder.getCategories().size()));
        topPanel.add(getLabel("Templates: " + crudHolder.getTemplates().size()));
        topPanel.add(getLabel("Intervals: " + crudHolder.getIntervals().size()));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        var closest = crudHolder.getClosestDate();
        String dataClosest;
        if (closest == null) {
            dataClosest = "No Events";
        } else {
            dataClosest = closest.format(formatter);
        }
        bottomPanel.add(getLabel("Closest event: " + dataClosest));


        var oldest = crudHolder.getOldestEvent();
        String dataOldest;
        if (oldest == null) {
            dataOldest = "No Events";
        } else {
            dataOldest = oldest.format(formatter);
        }
        bottomPanel.add(getLabel("Oldest event: " + dataOldest));

        statsPanel.add(topPanel);
        statsPanel.add(bottomPanel);
    }

    private void fillCategoriesData() {
        JPanel panel = new JPanel();
        var categories = crudHolder.getCategories();
        var events = crudHolder.getEvents();

        if (categories.isEmpty()) {
            panel.add(getLabel("There are no categories"));
            return;
        }

        for (Category category : categories) {
            long references = events.stream().map(e -> e.getCategories()).filter(lc -> lc.contains(category)).count();
            double percent = ((double) references / events.size()) * 100;
            panel.add(getLabel(category.getName() + " in " + references + " (" + percent + "%)"));
        }
        statsPanel.add(panel);
    }

    public void refreshData() {
        statsPanel.removeAll();
        if (isCategoriesDisplayed) {
            fillCategoriesData();
        } else {
            fillEventData();
        }

        // Methods needed to refresh statsPanel after categoriesButton is clicked
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private JLabel getLabel(String name) {
        return new JLabel("  " + name + "  ");
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
