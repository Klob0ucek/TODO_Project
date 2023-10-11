package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.action.AddAction;
import cz.muni.fi.pv168.project.ui.action.DeleteAction;
import cz.muni.fi.pv168.project.ui.action.EditAction;
import cz.muni.fi.pv168.project.ui.action.ExportAction;
import cz.muni.fi.pv168.project.ui.action.FilterAction;
import cz.muni.fi.pv168.project.ui.action.ImportAction;
import cz.muni.fi.pv168.project.ui.action.QuitAction;
import cz.muni.fi.pv168.project.ui.model.ComponentFactory;

import cz.muni.fi.pv168.project.ui.tab.Tab;
import cz.muni.fi.pv168.project.ui.tab.TabHolder;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainWindow {
    private final JFrame frame;

    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;
    private final Action exportAction;
    private final Action filterAction;
    private final Action importAction;
    private final Action quitAction;

    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final TabHolder tabHolder;

    public MainWindow() {
        frame = createFrame();
        frame.add(new JScrollPane(createTabs()), BorderLayout.CENTER);
        frame.add(createToolBar(), BorderLayout.WEST);
        frame.pack();

        tabHolder = new TabHolder(tabbedPane);

        addAction = new AddAction();
        deleteAction = new DeleteAction();
        editAction = new EditAction();
        exportAction = new ExportAction();
        filterAction = new FilterAction();
        importAction = new ImportAction();
        quitAction = new QuitAction();
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

    private JTabbedPane createTabs() {
        for (Tab tab : Tab.values()) {
            tabbedPane.addTab(
                    tab.getStringValue(),
                    null,
                    new JScrollPane(ComponentFactory.createTab(tab)),
                    tab.getTabTip()
            );
        }

        return tabbedPane;
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
