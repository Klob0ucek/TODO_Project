package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DateTimePicker;

import cz.muni.fi.pv168.project.todoapp.model.Category;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Filter {
    private final JCheckBox checkBox = new JCheckBox();
    private final JTextField name = new JTextField();
    private final JTextField location = new JTextField();
    private final JComboBox<Category> categories = new JComboBox<>();
    private final DateTimePicker fromDate = new DateTimePicker();
    private final DateTimePicker toDate = new DateTimePicker();

    public Filter(
            /* should have access to all currently existing Categories */
    ) {
        // ...
    }

    private static JComponent createNamedComponent(
            String name,
            JComponent toBeNamed
    ) {
        var named = new JPanel();
        named.add(new JLabel(name));
        named.add(toBeNamed);
        return named;
    }

    private static JComponent createNamedBlob(
            String xName,
            JComponent xComponent,
            String yName,
            JComponent yComponent
    ) {
        var names = new JPanel(new GridLayout(2, 1));
        names.add(new JLabel(xName));
        names.add(new JLabel(yName));

        var components = new JPanel(new GridLayout(2, 1));
        components.add(xComponent);
        components.add(yComponent);

        var blob = new JPanel();
        blob.add(names);
        blob.add(components);

        return blob;
    }

    public JComponent getFilterBar() {
        var filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        filterBar.add(createNamedComponent("Is done?:", checkBox));
        filterBar.add(createNamedBlob("Name:", name, "Location:", location));
        filterBar.add(createNamedComponent("Category:", categories));
        filterBar.add(createNamedBlob("From:", fromDate, "To:", toDate));

        return filterBar;
    }
}
