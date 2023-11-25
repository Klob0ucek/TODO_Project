package cz.muni.fi.pv168.project.todoapp.business.service.exeptions;

import java.io.Serial;

/**
 * Thrown, if there is already an existing entity.
 */
public class EntityAlreadyExistsException extends RuntimeApplicationException {

    @Serial
    private static final long serialVersionUID = 0L;

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
