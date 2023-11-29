package cz.muni.fi.pv168.project.todoapp.business.service.exeptions;

/**
 * Exception thrown when added or edited Category/Template/Interval does not have unique name
 */
public class ExistingNameException extends RuntimeApplicationException {

    public ExistingNameException(String message) {
        super(message);
    }

    public ExistingNameException(String userMessage, String message) {
        super(userMessage, message);
    }
}
