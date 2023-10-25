package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

public class Filter {
    private final JCheckBox isDoneCheckBox = new JCheckBox();
    private final JMenuBar categories = new JMenuBar();
    private final DatePicker fromDate = new DatePicker();
    private final DatePicker toDate = new DatePicker();
    private final JButton resetButton = new JButton("Reset");

    public Filter(
            /* should have access to all currently existing Categories */
    ) {
        resetButton.addActionListener(e -> { /* reset filters */ });
        /* ^^ or implement class that implements ActionListener ^^ */

        initCategories();
    }

    private void initCategories() {
        var menu = new JMenu("Category");
        for (var x : List.of("a", "b", "c", "d", "e", "Very long string, I'm curious what's gonna happen.")) {
            /*       ^^ list of available categories ^^ */
            menu.add(new JCheckBoxMenuItem(x));
        }
        categories.add(menu);
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

    public JComponent getFilterBar() {
        var filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        filterBar.add(createNamedComponent("Done?:", isDoneCheckBox));
        filterBar.add(categories);
        filterBar.add(createNamedVerticalPair("From:", fromDate, "To:", toDate));
        filterBar.add(resetButton);

        return filterBar;
    }
}
