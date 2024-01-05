package cz.muni.fi.pv168.project.todoapp.ui.dialog;

import cz.muni.fi.pv168.project.todoapp.business.model.Interval;
import cz.muni.fi.pv168.project.todoapp.business.model.UniqueIdProvider;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.time.Duration;

public class IntervalDialog extends EntityDialog<Interval> {
    private final JTextField nameField = new JTextField();
    private final JTextField abbreviationField = new JTextField();
    private final JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 525600, 1));

    private String guid;

    public IntervalDialog() {
        addFields();
        guid = UniqueIdProvider.newId();
    }

    public IntervalDialog(Interval interval) {
        addFields();
        guid = interval.getGuid();
        setFields(interval);
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
        return new Interval(guid, nameField.getText(), abbreviationField.getText(), Duration.ofMinutes(((Number) durationSpinner.getValue()).longValue()));
    }
}
