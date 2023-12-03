package cz.muni.fi.pv168.project.todoapp.ui.model;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Entity;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.CategoryListRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.DurationRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.util.ImportOption;

import javax.swing.table.AbstractTableModel;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicTableModel<T extends Entity> extends AbstractTableModel {
    protected final CrudService<T> crudService;
    protected List<T> rows;
    protected List<Column<T, ?>> columns;

    protected BasicTableModel(CrudService<T> crudService) {
        this.crudService = crudService;
        this.rows = new ArrayList<>(crudService.findAll());
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = columns.get(columnIndex).getValue(getEntity(rowIndex));

        if (o instanceof List) {
            return CategoryListRenderer.renderListCategory((List<Category>) o);
        } else if (o instanceof Duration) {
            return DurationRenderer.renderDuration((Duration) o);
        }
        return o;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getColumnType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // TODO should call crudService.update(entity) and then call refreshFromCrud()
        // but value needs to be converted to proper expected type
        T entity = getEntity(rowIndex);
        columns.get(columnIndex).setValue(value, entity);
    }

    public T getEntity(int rowIndex) {
        return rows.get(rowIndex);
    }

    public void deleteRow(int rowIndex) {
        T entityToBeDeleted = getEntity(rowIndex);
        crudService.deleteByGuid(entityToBeDeleted.getGuid());
        refreshFromCrud();
    }

    public void addRow(T row) {
        try {
            crudService.create(row);
        } finally {
            refreshFromCrud();
        }
    }

    public void updateRow(T row) {
        try {
            crudService.update(row);
        } finally {
            refreshFromCrud();
        }
    }

    public void refreshFromCrud() {
        this.rows = new ArrayList<>(crudService.findAll());
        fireTableDataChanged();
    }
}
