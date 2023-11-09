package cz.muni.fi.pv168.project.todoapp.ui;

import cz.muni.fi.pv168.project.todoapp.ui.action.event.ExportAction;
import cz.muni.fi.pv168.project.todoapp.ui.action.event.ImportAction;
import cz.muni.fi.pv168.project.todoapp.ui.dialog.NotificationDialog;
import cz.muni.fi.pv168.project.todoapp.ui.filter.Filter;
import cz.muni.fi.pv168.project.todoapp.ui.model.CategoryTableModel;
import cz.muni.fi.pv168.project.todoapp.ui.tab.GeneralTab;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabChangeListener;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabFactory;
import cz.muni.fi.pv168.project.todoapp.ui.tab.TabHolder;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MainWindow {
    private final JFrame frame = createFrame();
    private final List<GeneralTab> tabs = new ArrayList<>();

    public MainWindow() {
        JComponent verticalToolBar = new JPanel();
        ToolBarManager toolBarManager = new ToolBarManager(verticalToolBar, new ImportAction(frame), new ExportAction(frame));

        JTabbedPane tabbedPane = new JTabbedPane();
        TabHolder tabHolder = new TabHolder(tabbedPane, tabs);
        createTabs(toolBarManager, tabbedPane);

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

    private void createTabs(
            ToolBarManager toolBarManager,
            JTabbedPane tabbedPane
    ) {
        GeneralTab categoriesTab = TabFactory.createCategoriesTab(frame, toolBarManager);
        Supplier<CategoryTableModel> tableModelSupplier =
                () -> (CategoryTableModel) ((JTable) categoriesTab.getComponent()).getModel();

        tabs.addAll(
                List.of(
                        TabFactory.createEventsTab(frame, toolBarManager, tableModelSupplier),
                        categoriesTab,
                        TabFactory.createTemplatesTab(frame, toolBarManager, tableModelSupplier),
                        TabFactory.createIntervalsTab(frame, toolBarManager),
                        TabFactory.createHelpTab(frame, toolBarManager)
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
