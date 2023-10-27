package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;

import cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary.CheckboxGroup;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

public class Filter {
    private final JComboBox<IsDoneState> isDone = new JComboBox<>();
    private final JMenuBar categories = new JMenuBar();
    private final CheckboxGroup categoryOptions = new CheckboxGroup();
    private final DatePicker fromDate = new DatePicker();
    private final DatePicker toDate = new DatePicker();
    private final JMenuBar intervals = new JMenuBar();
    private final ButtonGroup intervalOptions = new ButtonGroup();
    private final JButton resetButton = new JButton("Reset");

    private enum IsDoneState {
        IRRELEVANT, YES, NO;
    }

    public Filter(
            /* should have access to all currently existing Categories and Intervals */
    ) {
        initCategories();
        initIntervals();

        isDone.setModel(new DefaultComboBoxModel<>(IsDoneState.values()));
        // isDone.setRenderer();

        resetButton.addActionListener(e -> {
            isDone.setSelectedIndex(0);
            categoryOptions.clearSelection();
            intervalOptions.clearSelection();
            fromDate.clear();
            toDate.clear();
        });
    }

    private static JComponent createNamedComponent(
            String name,
            JComponent component
    ) {
        var named = new JPanel();
        named.add(new JLabel(name));
        named.add(component);
        return named;
    }

    private static JComponent createNamedVerticalPair(
            String upperName,
            JComponent upperComponent,
            String lowerName,
            JComponent lowerComponent
    ) {
        var names = new JPanel(new GridLayout(2, 1));
        names.add(new JLabel(upperName));
        names.add(new JLabel(lowerName));

        var components = new JPanel(new GridLayout(2, 1));
        components.add(upperComponent);
        components.add(lowerComponent);

        var blob = new JPanel();
        blob.add(names);
        blob.add(components);

        return blob;
    }

    private void initCategories() {
        var menu = new JMenu("Category");
        JCheckBoxMenuItem option;

        for (var x : List.of("a", "b", "c", "d", "e", "Very long string, I'm curious what's gonna happen.")) {
            /*       ^^ list of available categories ^^ */
            option = new JCheckBoxMenuItem(x);
            menu.add(option);
            categoryOptions.add(option);
        }

        categories.add(menu);
    }

    private void initIntervals() {
        var menu = new JMenu("Interval");
        JRadioButtonMenuItem option;

        for (var x : List.of("a", "b", "c", "d", "e", "Very long string, I'm curious what's gonna happen.")) {
            /*       ^^ list of available intervals ^^ */
            option = new JRadioButtonMenuItem(x);
            menu.add(option);
            intervalOptions.add(option);
        }

        intervals.add(menu);
    }

    public JComponent getFilterBar() {
        var filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        filterBar.add(createNamedComponent("Done?:", isDone));
        filterBar.add(categories);
        filterBar.add(createNamedVerticalPair("From:", fromDate, "To:", toDate));
        filterBar.add(intervals);
        filterBar.add(resetButton);

        return filterBar;
    }
}
