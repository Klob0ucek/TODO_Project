package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;


import cz.muni.fi.pv168.project.todoapp.business.model.Entity;

/**
 * Class with static methods providing generic entity matchers.
 */
public class EntityMatchers {
    private EntityMatchers() {
    }

    /**
     * Creates new instance of {@link EntityMatcher} which results to true by any
     * given instance of the entity type {@link T}.
     *
     * @param <T> type for the created entity matcher
     * @return created entity matcher
     */
    public static <T extends Entity> EntityMatcher<T> all() {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(T entity) {
                return true;
            }
        };
    }
}
