package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

public class FailedToDeleteCategoryException extends DataStorageException {
    public FailedToDeleteCategoryException(String message) {
        super(message);
    }

    public FailedToDeleteCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToDeleteCategoryException(String userMessage, String message, Throwable cause) {
        super(userMessage, message, cause);
    }
}
