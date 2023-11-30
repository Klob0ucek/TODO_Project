package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import java.sql.Date;
import java.time.LocalDate;

public class DateTimeParser {
    public static LocalDate parseDate(Date date) {
        date.toLocalDate();
        return LocalDate.of(1, 1, 1);
    }
}
