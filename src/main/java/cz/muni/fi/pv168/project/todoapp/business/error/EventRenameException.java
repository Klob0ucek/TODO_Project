package cz.muni.fi.pv168.project.todoapp.business.error;

/**
 * This Exception is thrown added or edited event has a name, that already exists
 * Exceptions ic caught in AddEvent or EditEvent and appropriate notification is show to the user
 */
public class EventRenameException extends RuntimeApplicationException {
    public EventRenameException(String message) {
        super(message);
    }

    public EventRenameException(String oldName, String newName, String message) {
        super("Event with " + oldName + " was renamed to: " + newName, message);
    }
}
