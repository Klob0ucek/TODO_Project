package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.tab.GeneralTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabFactory;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

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

        createTabs(toolBarManager);
        addTabsToTabbedPane(tabbedPane);

        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));

        tabHolder.getCurrentTab().updateToolBar();

        frame.add(new Filter().getFilterBar(), BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(verticalToolBar, BorderLayout.WEST);
        frame.add(createBottomLine(), BorderLayout.SOUTH);
        frame.pack();
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
            ToolBarManager toolBarManager
    ) {
        tabs.addAll(
                List.of(
                        TabFactory.createEventsTab(toolBarManager),
                        TabFactory.createCategoriesTab(toolBarManager),
                        TabFactory.createTemplatesTab(toolBarManager),
                        TabFactory.createIntervalsTab(toolBarManager),
                        TabFactory.createHelpTab(toolBarManager)
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
