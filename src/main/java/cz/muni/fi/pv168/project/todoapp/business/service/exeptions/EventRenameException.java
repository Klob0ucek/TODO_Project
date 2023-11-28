package cz.muni.fi.pv168.project.todoapp.business.service.exeptions;

public class EventRenameException extends RuntimeApplicationException {
    public EventRenameException(String message) {
        super(message);
    }

    public EventRenameException(String oldName, String newName, String message) {
        super("Event with " + oldName + " was renamed to: " + newName, message);
    }
}
