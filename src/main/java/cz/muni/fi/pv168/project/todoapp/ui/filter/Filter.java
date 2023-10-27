package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;

import cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary.CheckboxGroup;

import cz.muni.fi.pv168.project.todoapp.ui.util.EnumUtils;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
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
    private final JMenuBar isDone = new JMenuBar();
    private final ButtonGroup isDoneOptions = new ButtonGroup();

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
        initIsDone();
        initCategories();
        initIntervals();

        resetButton.addActionListener(e -> {
            isDoneOptions.clearSelection();
            categoryOptions.clearSelection();
            fromDate.clear();
            toDate.clear();
            intervalOptions.clearSelection();
        });
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

    private void initIsDone() {
        var options = new JMenu("Done?");
        JRadioButtonMenuItem option;

        for (var optEnum : IsDoneState.values()) {
            option = new JRadioButtonMenuItem(EnumUtils.toTitle(optEnum));
            options.add(option);
            isDoneOptions.add(option);
        }

        isDone.add(options);
    }

    private void initCategories() {
        var options = new JMenu("Category");
        JCheckBoxMenuItem option;

        for (var x : List.of(
                "Irrelevant", "a", "b", "c", "d", "e", "Very long string, I'm curious what's gonna happen."
        )) {
            /*       ^^ list of available categories ^^ */
            option = new JCheckBoxMenuItem(x);
            options.add(option);
            categoryOptions.add(option);
        }

        categories.add(options);
    }

    private void initIntervals() {
        var options = new JMenu("Interval");
        JRadioButtonMenuItem option;

        for (var x : List.of(
                "Irrelevant", "a", "b", "c", "d", "e", "Very long string, I'm curious what's gonna happen."
        )) {
            /*       ^^ list of available intervals ^^ */
            option = new JRadioButtonMenuItem(x);
            options.add(option);
            intervalOptions.add(option);
        }

        intervals.add(options);
    }

    public JComponent getFilterBar() {
        var filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        filterBar.add(isDone);
        filterBar.add(categories);
        filterBar.add(createNamedVerticalPair("From:", fromDate, "To:", toDate));
        filterBar.add(intervals);
        filterBar.add(resetButton);

        return filterBar;
    }
}
