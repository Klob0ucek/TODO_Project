package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.tab.CategoriesTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.EventsTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.GeneralTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.HelpTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.IntervalsTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import cz.muni.fi.pv168.project.todoapp.ui.tab.TemplatesTab;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

public class MainWindow {
    private final JFrame frame = createFrame();
    private final ToolBarManager toolBarManager;
    private List<GeneralTab> tabs;

    public MainWindow() {
        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);

        JComponent verticalToolBar = new JPanel();
        toolBarManager = new ToolBarManager(verticalToolBar);

        createTabs(tabbedPane);

//        tabbedPane.setSelectedIndex(1);
        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));
//        tabbedPane.setSelectedIndex(0);

        frame.add(new JScrollPane(tabbedPane), BorderLayout.CENTER);
        frame.add(verticalToolBar, BorderLayout.WEST);
        frame.pack();
    }

    private void createTabs(
            JTabbedPane tabbedPane
    ) {
        tabs = List.of(
                new EventsTab(
                        "Events",
                        null,
                        ComponentFactory.createScheduleTable(),
                        null,
                        toolBarManager
                ),
                new CategoriesTab(
                        "Categories",
                        null,
                        ComponentFactory.createCategoryTable(),
                        null,
                        toolBarManager
                ),
                new TemplatesTab(
                        "Templates",
                        null,
                        ComponentFactory.createTemplateTable(),
                        null,
                        toolBarManager
                ),
                new IntervalsTab(
                        "Intervals",
                        null,
                        ComponentFactory.createIntervalTable(),
                        null,
                        toolBarManager
                ),
                new HelpTab(
                        "Help",
                        null,
                        ComponentFactory.createHelp(),
                        null,
                        toolBarManager
                )
        );

        for (var tab : tabs) {
            tab.addToPane(tabbedPane);
        }
    }

    private JFrame createFrame() {
        var frame = new JFrame("TODO App");
        frame.setMinimumSize(new Dimension(750, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }
}
