package cz.muni.fi.pv168.project.todoapp.ui.filter.matcher;

import cz.muni.fi.pv168.project.todoapp.business.model.Entity;
import cz.muni.fi.pv168.project.todoapp.ui.model.BasicTableModel;
import javax.swing.RowFilter;


/**
 * General entity matcher which can be extended by implementing the {@link EntityMatcher#evaluate(Object)}
 * method.
 *
 * @param <T> entity type
 */
public abstract class EntityMatcher<T extends Entity> extends RowFilter<BasicTableModel<T>, Integer> {

    @Override
    public boolean include(Entry<? extends BasicTableModel<T>, ? extends Integer> entry) {
        BasicTableModel<T> tableModel = entry.getModel();
        int rowIndex = entry.getIdentifier();
        T entity = tableModel.getEntity(rowIndex);

        return evaluate(entity);
    }

    public abstract boolean evaluate(T entity);
}
