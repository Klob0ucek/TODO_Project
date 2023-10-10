package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.model.ScheduleTableModel;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainWindow {
    private final JFrame frame;

    public MainWindow() {
        frame = createFrame();

        frame.add(new JScrollPane(createTabs()), BorderLayout.CENTER);
        frame.add(createToolBar(), BorderLayout.WEST);

        frame.pack();
    }

    private JTable createScheduleTableModel() {
        return new JTable(new ScheduleTableModel());
    }

    // only for testing
    // src: https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.addSeparator();

        toolBar.setFloatable(false);
        return toolBar;
    }

    private JTabbedPane createTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent scheduleTab = new JScrollPane(createScheduleTableModel());
        tabbedPane.addTab("Tasks", null, scheduleTab, "tasks_-_TIP");

        JComponent categoriesTab = makeTextPanel("categories_-_PLACEHOLDER");
        tabbedPane.addTab("Categories", null, categoriesTab, "categories_-_TIP");

        JComponent templatesTab = makeTextPanel("templates_-_PLACEHOLDER");
        tabbedPane.addTab("Templates", null, templatesTab, "templates_-_TIP");

        JComponent intervalsTab = makeTextPanel("intervals_-_PLACEHOLDER");
        tabbedPane.addTab("Intervals", null, intervalsTab, "intervals_-_TIP");

        JComponent statisticsTab = makeTextPanel("statistics_-_PLACEHOLDER");
        tabbedPane.addTab("Statistics", null, statisticsTab, "statistics_-_TIP");

        JComponent helpTab = makeTextPanel("help_-_PLACEHOLDER");
        tabbedPane.addTab("Help", null, helpTab, "help_-_TIP");

        return tabbedPane;
    }

    private JFrame createFrame() {
        var frame = new JFrame("TODO App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }
}
