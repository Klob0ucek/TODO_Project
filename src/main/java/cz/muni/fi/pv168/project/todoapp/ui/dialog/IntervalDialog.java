package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.time.Duration;

public class IntervalDialog extends EntityDialog<Interval> {
    private final JTextField nameField = new JTextField();
    private final JTextField abbreviationField = new JTextField();
    private final JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));

    private final Interval interval = new Interval();

    public IntervalDialog() {
        addFields();
    }

    public IntervalDialog(Interval interval) {
        addFields();
        makeCopy(interval);
        setFields(interval);
    }

    private void makeCopy(Interval interval) {
        this.interval.setName(interval.getName());
        this.interval.setAbbreviation(interval.getAbbreviation());
        this.interval.setDuration(interval.getDuration());
    }

    private void setFields(Interval interval) {
        nameField.setText(interval.getName());
        abbreviationField.setText(interval.getAbbreviation());
        durationSpinner.setValue(interval.getDuration().toMinutes());
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
        interval.setDuration(Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue()));
        return interval;
    }
}
