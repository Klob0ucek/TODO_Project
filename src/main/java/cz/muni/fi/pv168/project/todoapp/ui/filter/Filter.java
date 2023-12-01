package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.ui.filter.components.FilterComboBoxBuilder;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterCategoryValues;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterDoneValues;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.CategoryRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.SpecialFilterCategoryValuesRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.SpecialFilterDoneValuesRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomDatePickerSettings;
import cz.muni.fi.pv168.project.todoapp.utils.Either;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Class holding all GUI components for filters.
 */
public class Filter {
    private final JComboBox<Either<SpecialFilterDoneValues, Boolean>> doneComboBox;
    private final JComboBox<Either<SpecialFilterCategoryValues, Category>> categoryComboBox;
    private final EventTableFilter eventTableFilter;
    private final CrudHolder crudHolder;
    private final DatePicker fromDate = new DatePicker(CustomDatePickerSettings.getSettings());
    private final DatePicker toDate = new DatePicker(CustomDatePickerSettings.getSettings());
    private final JSpinner intervalLower;
    private final JSpinner intervalUpper;
    private final JButton resetButton = new JButton("Reset");


    public Filter(
            CrudHolder crudHolder,
            EventTableFilter eventTableFilter
    ) {
        this.crudHolder = crudHolder;
        this.eventTableFilter = eventTableFilter;

        this.doneComboBox = createDoneFilter(eventTableFilter);
        this.categoryComboBox = createCategoryFilter(eventTableFilter);
        this.intervalLower = new JSpinner(
                new SpinnerNumberModel(crudHolder.getLowestDuration(), 0, 525600, 5));
        this.intervalUpper = new JSpinner(
                new SpinnerNumberModel(crudHolder.getHighestDuration(), 0, 525600, 5));
        initIntervals();
        initDates();
        resetButton.addActionListener(e -> resetFilters());

        resetFilters();
    }

    private JComboBox<Either<SpecialFilterDoneValues, Boolean>> createDoneFilter(
            EventTableFilter eventTableFilter) {
        return FilterComboBoxBuilder.create(SpecialFilterDoneValues.class, new Boolean[0])
                .setSelectedItem(SpecialFilterDoneValues.ALL)
                .setSpecialValuesRenderer(new SpecialFilterDoneValuesRenderer())
                .setFilter(eventTableFilter::filterDone)
                .build();
    }

    private JComboBox<Either<SpecialFilterCategoryValues, Category>> createCategoryFilter(
            EventTableFilter eventTableFilter) {
        return FilterComboBoxBuilder.create(SpecialFilterCategoryValues.class, crudHolder.getCategories().toArray(Category[]::new))
                .setSelectedItem(SpecialFilterCategoryValues.ALL)
                .setSpecialValuesRenderer(new SpecialFilterCategoryValuesRenderer())
                .setValuesRenderer(new CategoryRenderer())
                .setFilter(eventTableFilter::filterCategory)
                .build();
    }

    private void initDates() {
        fromDate.addDateChangeListener(e -> eventTableFilter.filterFromDate(fromDate.getDate()));
        toDate.addDateChangeListener(e -> eventTableFilter.filterToDate(toDate.getDate()));
    }

    private void initIntervals() {
        intervalLower.addChangeListener(e -> eventTableFilter.filterLowerDuration((int) intervalLower.getValue()));
        intervalUpper.addChangeListener(e -> eventTableFilter.filterUpperDuration((int) intervalUpper.getValue()));
    }

    private JComponent createNamedComponent(String name, JComponent toBeNamed) {
        var named = new JPanel();
        named.add(new JLabel(name));
        named.add(toBeNamed);
        return named;
    }

    private static JComponent createNamedVerticalPair(
            String upperName,
            JComponent upperComponent,
            String lowerName,
            JComponent lowerComponent
    ) {
        var names = new JPanel(new GridLayout(2, 1));
        names.add(new JLabel(upperName));
        names.add(new JLabel(lowerName));

        var components = new JPanel(new GridLayout(2, 1));
        components.add(upperComponent);
        components.add(lowerComponent);

        var blob = new JPanel();
        blob.add(names);
        blob.add(components);

        return blob;
    }

    /**
     * This method checks whether Duration Filter needs to be updated
     * It is updated when Interval Filter was not tempered with and if new Duration is lower or
     * higher that previous max or min.
     *
     * @param newDuration of event that will be added
     */

    public void updateIntervals(int newDuration) {
        if ((int) intervalUpper.getValue() == crudHolder.getHighestDuration() &&
                (int) intervalLower.getValue() == crudHolder.getLowestDuration()) {
            // Filters are set on default value
            if ((int) intervalLower.getValue() > newDuration) {
                intervalLower.setValue(newDuration);
            } else if ((int) intervalUpper.getValue() < newDuration) {
                intervalUpper.setValue(newDuration);
            }
        }
    }

    public void resetFilters() {
        doneComboBox.setSelectedItem(doneComboBox.getItemAt(0));
        categoryComboBox.setSelectedItem(categoryComboBox.getItemAt(0));
        fromDate.clear();
        toDate.clear();
        intervalLower.setValue(crudHolder.getLowestDuration());
        intervalUpper.setValue(crudHolder.getHighestDuration());
    }

    public JComponent getFilterBar() {
        var filterBar = new JPanel(new FlowLayout(FlowLayout.CENTER));

        filterBar.add(createNamedComponent("Status:", doneComboBox));
        filterBar.add(createNamedComponent("Category:", categoryComboBox));
        filterBar.add(createNamedVerticalPair("From:", fromDate, "To:", toDate));
        filterBar.add(createNamedVerticalPair("Lower duration bound:", intervalLower,
                "Upper duration bound:", intervalUpper));
        filterBar.add(resetButton);

        return filterBar;
    }
}
