package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.action.ActionType;
import cz.muni.fi.pv168.project.todoapp.ui.action.AddAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.DeleteAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.EditAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.FilterAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.ImportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.QuitAction;
import cz.muni.fi.pv168.project.todoapp.ui.tab.Tab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.Action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private final JFrame frame = createFrame();

    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;
    private final Action exportAction;
    private final Action filterAction;
    private final Action importAction;
    private final Action quitAction;

    private final List<Action> actions;

    private final List<Tab> tabs = new ArrayList<>();

    public MainWindow() {
        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);

        addAction = new AddAction(tabHolder);
        deleteAction = new DeleteAction(tabHolder);
        editAction = new EditAction(tabHolder);
        exportAction = new ExportAction(tabHolder);
        filterAction = new FilterAction(tabHolder);
        importAction = new ImportAction(tabHolder);
        quitAction = new QuitAction();

        actions = List.of(
                addAction, deleteAction, editAction,
                exportAction, filterAction, importAction
        );

        createTabs(tabbedPane);

//        tabbedPane.setSelectedIndex(1);
        tabbedPane.addChangeListener(new TabChangeListener(tabHolder));
//        tabbedPane.setSelectedIndex(0);

        frame.add(new JScrollPane(tabbedPane), BorderLayout.CENTER);
        frame.add(createToolBar(), BorderLayout.WEST);
        frame.pack();
    }

    private JToolBar createVerticalToolBar() {
        JToolBar verticalTools = new JToolBar();
        verticalTools.setLayout(new BoxLayout(verticalTools, BoxLayout.Y_AXIS));
        verticalTools.setFloatable(false);

        return verticalTools;
    }

    private JToolBar northTools() {
        JToolBar toolBar = createVerticalToolBar();

        toolBar.add(filterAction);
        toolBar.addSeparator();
        toolBar.add(addAction);
        toolBar.add(editAction);
        toolBar.add(deleteAction);

        return toolBar;
    }

    private JToolBar southTools() {
        JToolBar toolBar = createVerticalToolBar();

        toolBar.add(importAction);
        toolBar.add(exportAction);
        toolBar.addSeparator();
        toolBar.add(quitAction);

        return toolBar;
    }

    private JPanel createToolBar() {
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BorderLayout());

        toolBar.add(northTools(), BorderLayout.NORTH);
        toolBar.add(southTools(), BorderLayout.SOUTH);

        return toolBar;
    }

    private void createTabs(
            JTabbedPane tabbedPane
    ) {
        tabs.addAll(
                List.of(
                        new Tab
                                .Builder("Tasks", ComponentFactory.createScheduleTable(), actions)
                                .enableActions(ActionType.all())
                                .build(),
                        new Tab
                                .Builder("Categories", ComponentFactory.createCategoryTable(), actions)
                                .enableActions(ActionType.basic())
                                .build(),
                        new Tab
                                .Builder("Templates", ComponentFactory.createTemplateTable(), actions)
                                .enableActions(ActionType.basic())
                                .build(),
                        new Tab
                                .Builder("Intervals", ComponentFactory.createIntervalTable(), actions)
                                .enableActions(ActionType.basic())
                                .build(),
                        new Tab
                                .Builder("Help", ComponentFactory.createHelp(), actions)
                                .build()
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
