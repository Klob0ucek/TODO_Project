package cz.muni.fi.pv168.project.todoapp.ui.filter;

import com.github.lgooddatepicker.components.DatePicker;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import cz.muni.fi.pv168.project.todoapp.ui.filter.components.FilterComboBoxBuilder;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterCategoryValues;
import cz.muni.fi.pv168.project.todoapp.ui.filter.values.SpecialFilterDoneValues;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.CategoryRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.SpecialFilterCategoryValuesRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.renderer.SpecialFilterDoneValuesRenderer;
import cz.muni.fi.pv168.project.todoapp.ui.settings.CustomDatePickerSettings;
import cz.muni.fi.pv168.project.todoapp.utils.Either;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Class holding all GUI components for filters.
 */
public class Filter {
    private final JComboBox<Either<SpecialFilterDoneValues, Boolean>> doneComboBox;
    private final DefaultComboBoxModel<Category> categoriesModel;
    private JComboBox<Either<SpecialFilterCategoryValues, Category>> categoryComboBox;
    private final EventTableFilter eventTableFilter;
    private final CrudService<Event> eventCrudService;
    private final DatePicker fromDate = new DatePicker(CustomDatePickerSettings.getSettings());
    private final DatePicker toDate = new DatePicker(CustomDatePickerSettings.getSettings());
    private final JSpinner intervalLower;
    private final JSpinner intervalUpper;
    private final JButton resetButton = new JButton("Reset");


    public Filter(
            CrudService<Event> eventCrudService,
            EventTableFilter eventTableFilter,
            DefaultComboBoxModel<Category> catsModel
    ) {
        this.eventCrudService = eventCrudService;
        this.eventTableFilter = eventTableFilter;
        this.categoriesModel = catsModel;
        this.doneComboBox = createDoneFilter(eventTableFilter);
        this.categoryComboBox = createCategoryFilter(eventTableFilter);

        var events = eventCrudService.findAll();

        this.intervalLower = new JSpinner(
                new SpinnerNumberModel(EventTableFilter.getLowestDuration(events), 0, 525600, 5));
        this.intervalUpper = new JSpinner(
                new SpinnerNumberModel(EventTableFilter.getHighestDuration(events), 0, 525600, 5));
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
        return FilterComboBoxBuilder.create(SpecialFilterCategoryValues.class, categoriesModel)
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

    public void resetIntervals() {
        var events = eventCrudService.findAll();
        intervalLower.setValue(EventTableFilter.getLowestDuration(events));
        intervalUpper.setValue(EventTableFilter.getHighestDuration(events));
    }

    public void resetFilters() {
        doneComboBox.setSelectedItem(doneComboBox.getItemAt(0));
        categoryComboBox.setSelectedItem(categoryComboBox.getItemAt(0));
        fromDate.clear();
        toDate.clear();
        resetIntervals();
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
