package cz.muni.fi.pv168.project.todoapp.business.model;

import java.util.Objects;

public abstract class Entity {

    protected String guid;

    protected Entity() {
        this.guid = UniqueIdProvider.newId();
    }

    protected Entity(String guid) {
        this.guid = guid;
    }


    /**
     * Returns globally unique identifier of the given entity.
     */
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(guid, entity.guid);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(guid);
    }
}
