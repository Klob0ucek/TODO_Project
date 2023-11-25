package cz.muni.fi.pv168.project.todoapp.business.service.exeptions;

public class NameAlreadyExistException extends RuntimeApplicationException {
    public NameAlreadyExistException(String message) {
        super(message);
    }

    public NameAlreadyExistException(String oldName, String newName, String message) {
        super("Event with " + oldName + " was renamed to: " + newName, message);
    }
}
