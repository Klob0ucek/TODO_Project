package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

public class IntervalAbbrevNotUniqueException extends DataStorageException {
    public IntervalAbbrevNotUniqueException(String message) {
        super(message);
    }

    public IntervalAbbrevNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntervalAbbrevNotUniqueException(String userMessage, String message, Throwable cause) {
        super(userMessage, message, cause);
    }
}
