package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.action.AddAction;
import cz.muni.fi.pv168.project.ui.action.DeleteAction;
import cz.muni.fi.pv168.project.ui.action.EditAction;
import cz.muni.fi.pv168.project.ui.action.ExportAction;
import cz.muni.fi.pv168.project.ui.action.FilterAction;
import cz.muni.fi.pv168.project.ui.action.ImportAction;
import cz.muni.fi.pv168.project.ui.action.QuitAction;
import cz.muni.fi.pv168.project.ui.model.ComponentFactory;

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

    private final Action addAction = new AddAction();
    private final Action deleteAction = new DeleteAction();
    private final Action editAction = new EditAction();
    private final Action exportAction = new ExportAction();
    private final Action filterAction = new FilterAction();
    private final Action importAction = new ImportAction();
    private final Action quitAction = new QuitAction();

    private final JTabbedPane tabbedPane = new JTabbedPane();

    public MainWindow() {
        frame = createFrame();

        frame.add(new JScrollPane(createTabs()), BorderLayout.CENTER);
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
