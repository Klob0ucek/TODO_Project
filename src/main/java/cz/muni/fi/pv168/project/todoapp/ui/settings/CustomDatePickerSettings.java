package cz.muni.fi.pv168.project.todoapp.ui.settings;


import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.awt.*;

public abstract class CustomDatePickerSettings {
    private static final Color backgroundColor = new Color(70, 73, 75);
    private static final Color textColor = Color.WHITE;

    public static DatePickerSettings getSettings() {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setColor(DateArea.TextFieldBackgroundInvalidDate, backgroundColor);
        settings.setColor(DateArea.TextFieldBackgroundValidDate, backgroundColor);
        settings.setColor(DateArea.CalendarBackgroundNormalDates, backgroundColor);
        settings.setColor(DateArea.BackgroundOverallCalendarPanel, backgroundColor);
        settings.setColor(DateArea.BackgroundTodayLabel, backgroundColor);
        settings.setColor(DateArea.BackgroundClearLabel, backgroundColor);
        settings.setColor(DateArea.BackgroundMonthAndYearMenuLabels, backgroundColor);
        settings.setColor(DateArea.CalendarTextNormalDates, textColor);
        settings.setColor(DateArea.DatePickerTextValidDate, textColor);
        return settings;
    }
}
