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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private final JFrame frame = createFrame();
    private final List<GeneralTab> tabs = new ArrayList<>();

    public MainWindow() {
        JComponent verticalToolBar = new JPanel();
        ToolBarManager toolBarManager = new ToolBarManager(verticalToolBar);
        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);

        createTabs(toolBarManager, tabHolder);
        addTabsToTabbedPane(tabbedPane);

        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));

        tabHolder.getCurrentTab().updateToolBar();

        frame.add(createTopPanel(), BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(verticalToolBar, BorderLayout.WEST);
        frame.add(createBottomLine(), BorderLayout.SOUTH);
        frame.pack();
    }

    private JLabel createTopPanel() {
        return new JLabel("TOP-SIDE");
    }

    private JLabel createBottomLine() {
        return new JLabel("BOTTOM-SIDE");
    }

    private void addTabsToTabbedPane(
            JTabbedPane tabbedPane
    ) {
        for (var tab : tabs) {
            tab.addToPane(tabbedPane);
        }
    }

    private void createTabs(
            ToolBarManager toolBarManager,
            TabHolder tabHolder
    ) {
        tabs.addAll(
                List.of(
                        new EventsTab(
                                "Events",
                                null,
                                ComponentFactory.createScheduleTable(),
                                null,
                                toolBarManager,
                                tabHolder
                        ),
                        new CategoriesTab(
                                "Categories",
                                null,
                                ComponentFactory.createCategoryTable(),
                                null,
                                toolBarManager,
                                tabHolder
                        ),
                        new TemplatesTab(
                                "Templates",
                                null,
                                ComponentFactory.createTemplateTable(),
                                null,
                                toolBarManager,
                                tabHolder
                        ),
                        new IntervalsTab(
                                "Intervals",
                                null,
                                ComponentFactory.createIntervalTable(),
                                null,
                                toolBarManager,
                                tabHolder
                        ),
                        new HelpTab(
                                "Help",
                                null,
                                ComponentFactory.createHelp(),
                                null,
                                toolBarManager,
                                tabHolder
                        )
                )
        );
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
