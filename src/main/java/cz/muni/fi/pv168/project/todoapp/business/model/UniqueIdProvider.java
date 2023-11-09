package cz.muni.fi.pv168.project.todoapp.business.model;

import java.util.UUID;

public class UniqueIdProvider {

    public static String newId() {
        return UUID.randomUUID().toString();
    }
}
