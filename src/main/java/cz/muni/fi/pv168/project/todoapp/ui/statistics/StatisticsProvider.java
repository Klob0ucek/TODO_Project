package cz.muni.fi.pv168.project.todoapp.ui.statistics;

import cz.muni.fi.pv168.project.todoapp.business.model.AbstractCoreEvent;
import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.business.model.Event;
import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudService;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsProvider {
    private final CrudService<Event> eventCrudService;
    private final CrudService<Category> categoryCrudService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public StatisticsProvider(CrudService<Event> eventCrudService, CrudService<Category> categoryCrudService) {
        this.eventCrudService = eventCrudService;
        this.categoryCrudService = categoryCrudService;
    }

    public void fillEventData(JPanel statsPanel) {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        var events = eventCrudService.findAll();

        topPanel.add(getLabel("Events total: " + events.size()));
        topPanel.add(getLabel("Planned events: " + getPlannedEventsCount(events)));
        topPanel.add(getLabel("Finished events: " + getDoneEventsCount(events)));
        var hoursPart = getEventsDurationTillToday(events).toHoursPart();
        var minutes = getEventsDurationTillToday(events).toMinutesPart();
        topPanel.add(getLabel("Duration of events till today: " + hoursPart + " hours " + minutes + " minutes"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        var closest = getClosestDate(events);
        String dataClosest;
        if (closest == null) {
            dataClosest = "No Events";
        } else {
            dataClosest = closest.format(formatter);
        }
        bottomPanel.add(getLabel("Closest event: " + dataClosest));


        var oldest = getOldestEvent(events);
        String dataOldest;
        if (oldest == null) {
            dataOldest = "No Events";
        } else {
            dataOldest = oldest.format(formatter);
        }
        bottomPanel.add(getLabel("Oldest event: " + dataOldest));


        statsPanel.add(topPanel);
        statsPanel.add(bottomPanel);
    }

    public void fillCategoriesData(JPanel statsPanel) {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        var events = eventCrudService.findAll();
        var categories = categoryCrudService.findAll();

        if (categories.isEmpty()) {
            topPanel.add(getLabel("There are no categories"));
            statsPanel.add(topPanel);
            return;
        }

        int row = 0;
        for (Category category : categories) {
            long references = events.stream().map(AbstractCoreEvent::getCategories).filter(lc -> lc.contains(category)).count();
            double percent = ((double) references / events.size()) * 100;
            if (row < 6) {
                topPanel.add(getLabel(String.format("%s: %d (%.2f%%)", category.getName(), references, percent)));
            } else {
                bottomPanel.add(getLabel(String.format("%s: %d (%.2f%%)", category.getName(), references, percent)));
            }
            row++;
        }
        statsPanel.add(topPanel);
        statsPanel.add(bottomPanel);
    }

    private JLabel getLabel(String name) {
        return new JLabel("  " + name + "  ");
    }

    private LocalDate getClosestDate(List<Event> events) {
        var dateList = events.stream().map(Event::getDate).toList();
        if (dateList.isEmpty()) {
            return null;
        }
        LocalDate today = LocalDate.now();
        LocalDate closestFutureDate = null;

        for (LocalDate date : dateList) {
            if (date == null) {
                continue;
            }
            if (date.isAfter(today) && (closestFutureDate == null || date.isBefore(closestFutureDate))) {
                closestFutureDate = date;
            }
        }
        return closestFutureDate;
    }

    public LocalDate getOldestEvent(List<Event> events) {
        var dateList = events.stream().map(Event::getDate).toList();
        if (dateList.isEmpty()) {
            return null;
        }
        LocalDate oldestDate = null;

        for (LocalDate date : dateList) {
            if (date == null) {
                continue;
            }
            if ((oldestDate == null) || date.isBefore(oldestDate)) {
                oldestDate = date;
            }
        }

        return oldestDate;
    }

    public Duration getEventsDurationTillToday(List<Event> events) {
        Duration totalDuration = Duration.ZERO;
        LocalDate today = LocalDate.now();
        for (Event event : events) {
            if (event.getDate() != null && today.isAfter(event.getDate())) {
                totalDuration = totalDuration.plus(event.getDuration());
            }
        }
        return totalDuration;
    }

    public long getDoneEventsCount(List<Event> events) {
        return events.stream().filter((AbstractCoreEvent::isDone)).count();
    }

    public long getPlannedEventsCount(List<Event> events) {
        return events.size() - getDoneEventsCount(events);
    }
}
