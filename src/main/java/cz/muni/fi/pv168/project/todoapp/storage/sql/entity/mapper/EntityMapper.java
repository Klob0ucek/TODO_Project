package cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper;

/**
 * Map from one entity to another
 * We are using this mappers map between the business models and database entities
 *
 * @param <D> database type
 * @param <B> business entity type
 */
public interface EntityMapper<D, B> {

    /**
     * Map database entity to business entity
     *
     * @param entity database entity
     * @return business entity
     */
    B mapToBusiness(D entity);

    /**
     * Map new business entity to database entity
     *
     * @param entity business entity
     * @return database entity
     */
    D mapNewEntityToDatabase(B entity);

    /**
     * Map existing business entity to database entity
     *
     * @param entity existing business entity
     * @param dbId   existing entity db id
     * @return database entity
     */
    D mapExistingEntityToDatabase(B entity, Long dbId);
}
