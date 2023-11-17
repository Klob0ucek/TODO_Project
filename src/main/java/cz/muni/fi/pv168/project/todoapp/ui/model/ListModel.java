package cz.muni.fi.pv168.project.todoapp.ui.model;

import javax.swing.AbstractListModel;
import java.util.List;

public class ListModel<E> extends AbstractListModel<E> {
    private final List<E> entities;

    public ListModel(List<E> entities) {
        this.entities = entities;
    }

    @Override
    public int getSize() {
        return entities.size();
    }

    @Override
    public E getElementAt(int index) {
        return entities.get(index);
    }
}
