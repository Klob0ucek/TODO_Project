package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DateTimePicker;

import cz.muni.fi.pv168.project.todoapp.model.Category;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Filter {
    private final JCheckBox isDoneCheckBox = new JCheckBox();
    private final JTextField locationInput = new JTextField();
    private final JTextField nameInput = new JTextField();
    // width of input fields above should be adjusted -> minimal window width too
    private final DateTimePicker fromDate = new DateTimePicker();
    private final JComboBox<Category> categories = new JComboBox<>();
    private final DateTimePicker toDate = new DateTimePicker();
    private final JButton resetButton = new JButton("Reset");

    public Filter(
            /* should have access to all currently existing Categories */
    ) {
        resetButton.addActionListener(e -> { /* reset filters */ });
        /* ^^ or implement class that implements ActionListener ^^ */
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

        filterBar.add(createNamedComponent("Is done?:", isDoneCheckBox));
        filterBar.add(createNamedVerticalPair("Name:", nameInput, "Location:", locationInput));
        filterBar.add(createNamedComponent("Category:", categories));
        filterBar.add(createNamedVerticalPair("From:", fromDate, "To:", toDate));
        filterBar.add(resetButton);

        return filterBar;
    }
}
