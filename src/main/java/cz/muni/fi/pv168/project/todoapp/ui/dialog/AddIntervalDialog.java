package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.time.Duration;

public class AddIntervalDialog extends EntityDialog<Interval> {
    private final JTextField nameField = new JTextField();
    private final JTextField abbreviationField = new JTextField();
    private final JSpinner durationSpinner = new JSpinner();

    public AddIntervalDialog() {
        addFields();
    }

    private void addFields() {
        add("Name:", nameField);
        add("Abbreviation:", abbreviationField);
        add("Duration:", durationSpinner);
    }

    @Override
    Interval getEntity() {
        String name = nameField.getText();
        String abbreviation = abbreviationField.getText();
        Duration duration = Duration.ofMinutes((Integer) durationSpinner.getValue());
        return new Interval(name, abbreviation, duration);
    }
}
