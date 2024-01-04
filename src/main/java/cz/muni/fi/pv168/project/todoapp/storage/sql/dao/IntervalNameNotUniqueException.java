package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

public class IntervalNameNotUniqueException extends DataStorageException {
    public IntervalNameNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
