package cz.muni.fi.pv168.project.todoapp.ui.filter.components;


import cz.muni.fi.pv168.project.todoapp.ui.model.CustomValuesModelDecorator;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.AbstractRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.EitherRenderer;
import cz.muni.fi.pv168.project.todoapp.utils.Either;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.util.function.Consumer;

/**
 * Builder for {@link JComboBox} with {@link Either} as elements.
 *
 * @param <L> Enum with special selectable values.
 * @param <R> Type of ordinary selectable values (entities).
 */
public class FilterComboBoxBuilder<L extends Enum<L>, R> {
    private final Class<L> clazz;
    private final DefaultComboBoxModel<R> values;
    private AbstractRenderer<L> specialValuesRenderer;
    private AbstractRenderer<R> valuesRenderer;
    private Either<L, R> selectedItem;
    private Consumer<Either<L, R>> filter;

    private FilterComboBoxBuilder(Class<L> clazz, DefaultComboBoxModel<R> values) {
        this.clazz = clazz;
        this.values = values;
    }


    public static <L extends Enum<L>, R> FilterComboBoxBuilder<L, R> create(Class<L> clazz, R[] values) {
        return new FilterComboBoxBuilder<>(clazz, new DefaultComboBoxModel<>(values));
    }

    public static <L extends Enum<L>, R> FilterComboBoxBuilder<L, R> create(Class<L> clazz, DefaultComboBoxModel<R> valuesModel) {
        return new FilterComboBoxBuilder<>(clazz, valuesModel);
    }

    public JComboBox<Either<L, R>> build() {
        var comboBox = new JComboBox<>(CustomValuesModelDecorator.addCustomValues(clazz, values));
        comboBox.setRenderer(EitherRenderer.create(specialValuesRenderer, valuesRenderer));
        comboBox.addActionListener(e -> filter.accept(comboBox.getItemAt(comboBox.getSelectedIndex())));
        if (selectedItem != null) {
            comboBox.setSelectedItem(selectedItem);
        }

        return comboBox;
    }

    public FilterComboBoxBuilder<L, R> setSpecialValuesRenderer(AbstractRenderer<L> specialValuesRenderer) {
        this.specialValuesRenderer = specialValuesRenderer;
        return this;
    }

    public FilterComboBoxBuilder<L, R> setValuesRenderer(AbstractRenderer<R> valuesRenderer) {
        this.valuesRenderer = valuesRenderer;
        return this;
    }

    public FilterComboBoxBuilder<L, R> setSelectedItem(L selectedItem) {
        this.selectedItem = Either.left(selectedItem);
        return this;
    }

    public FilterComboBoxBuilder<L, R> setSelectedItem(R selectedItem) {
        this.selectedItem = Either.right(selectedItem);
        return this;
    }

    public FilterComboBoxBuilder<L, R> setFilter(Consumer<Either<L, R>> filter) {
        this.filter = filter;
        return this;
    }
}
