package cz.muni.fi.pv168.project.todoapp.business.service.exeptions;

public class EventNameException extends RuntimeApplicationException {
    public EventNameException(String message) {
        super(message);
    }

    public EventNameException(String oldName, String newName, String message) {
        super("Event with " + oldName + " was renamed to: " + newName, message);
    }
}
