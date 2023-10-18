package cz.muni.fi.pv168.todo.project.ui.dialog;

import cz.muni.fi.pv168.todo.project.model.Interval;

import javax.swing.*;
import java.time.Duration;

public class AddIntervalDialog extends EntityDialog<Interval> {
    private final JTextField nameField = new JTextField();
    private final JTextField abbreviationField = new JTextField();
    private final JSpinner durationSpinner = new JSpinner();

    private final Interval interval;

    public AddIntervalDialog(Interval interval) {
        this.interval = interval;
        addFields();
    }

    private void addFields() {
        add("Name:", nameField);
        add("Abbreviation:", abbreviationField);
        add("Duration:", durationSpinner);
    }

    @Override
    Interval getEntity() {
        interval.setName(nameField.getText());
        interval.setAbbreviation(abbreviationField.getText());
        interval.setDuration(Duration.ofMinutes((Integer) durationSpinner.getValue()));
        return interval;
    }
}
