package cz.muni.fi.pv168.project.todoapp.ui.settings;

import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.awt.*;

public abstract class CustomTimePickerSettings {
    private static final Color backgroundColor = new Color(70, 73, 75);
    private static final Color textColor = Color.WHITE;

    public static TimePickerSettings getSettings() {
        TimePickerSettings settings = new TimePickerSettings();
        settings.use24HourClockFormat();
        settings.setColor(TimeArea.TimePickerTextInvalidTime, backgroundColor);
        settings.setColor(TimeArea.TimePickerTextValidTime, backgroundColor);
        settings.setColor(TimeArea.TextFieldBackgroundValidTime, backgroundColor);
        settings.setColor(TimeArea.TimePickerTextValidTime, textColor);
        return settings;
    }
}
