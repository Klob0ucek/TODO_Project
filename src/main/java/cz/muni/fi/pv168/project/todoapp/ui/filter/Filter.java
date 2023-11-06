package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;

import cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary.CheckGroup;
import cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary.OptionGroup;
import cz.muni.fi.pv168.project.todoapp.ui.filter.auxiliary.RadioGroup;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomDatePickerSettings;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomTimePickerSettings;
import cz.muni.fi.pv168.project.todoapp.ui.util.EnumUtils;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Filter {
    private final JMenuBar isDone = new JMenuBar();
    private final RadioGroup isDoneOptions = new RadioGroup();

    private final JMenuBar categories = new JMenuBar();
    private final CheckGroup categoryOptions = new CheckGroup();

    private final DatePicker fromDate = new DatePicker(CustomDatePickerSettings.getSettings());
    private final DatePicker toDate = new DatePicker(CustomDatePickerSettings.getSettings());

    private final JMenuBar intervals = new JMenuBar();
    private final RadioGroup intervalOptions = new RadioGroup();

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

        resetButton.addActionListener(e -> resetFilters());

        resetFilters();
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

    private static <T extends JMenuItem> void initializer(
            String name,
            Function<String, T> optionConstructor,
            List<String> optionValues,
            JMenuBar optionMenuBar,
            OptionGroup<T> optionGroup
    ) {
        var optionsMenu = new JMenu(name);
        T option;

        for (var optVal : optionValues) {
            option = optionConstructor.apply(optVal);
            optionsMenu.add(option);
            optionGroup.add(option);
        }

        optionMenuBar.add(optionsMenu);
    }

    public void resetFilters() {
        isDoneOptions.setDefault();
        categoryOptions.setDefault();
        fromDate.clear();
        toDate.clear();
        intervalOptions.setDefault();
    }

    private void initIsDone() {
        initializer(
                "Done?",
                JRadioButtonMenuItem::new,
                Arrays.stream(IsDoneState.values()).map(EnumUtils::toTitle).toList(),
                isDone,
                isDoneOptions
        );
    }

    private void initCategories() {
        initializer(
                "Category",
                JCheckBoxMenuItem::new,
                List.of("a", "b", "c", "Very long string, I'm curious what's gonna happen."),
                categories,
                categoryOptions
        );
    }

    private void initIntervals() {
        initializer(
                "Interval",
                JRadioButtonMenuItem::new,
                List.of("Irrelevant", "a", "b", "c", "Very long string, I'm curious what's gonna happen."),
                intervals,
                intervalOptions
        );
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
