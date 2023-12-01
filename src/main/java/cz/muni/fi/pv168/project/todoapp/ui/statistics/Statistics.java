package cz.muni.fi.pv168.project.todoapp.ui.statistics;

import cz.muni.fi.pv168.project.todoapp.business.service.crud.CrudHolder;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Statistics {
    private final CrudHolder crudHolder;

    public Statistics(CrudHolder crudHolder) {
        this.crudHolder = crudHolder;
    }

    public Component getStats() {
        JPanel panel = new JPanel();
        new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.add(getLabel("Planned events: ", crudHolder.getPlannedEventsCount()));
        panel.add(getLabel("Finished events: ", crudHolder.getDoneEventsCount()));
        panel.add(getLabel("Events total: ", crudHolder.getEvents().size()));
        panel.add(getLabel("Categories: ", crudHolder.getCategories().size()));
        panel.add(getLabel("Templates: ", crudHolder.getTemplates().size()));
        panel.add(getLabel("Intervals: ", crudHolder.getIntervals().size()));

        return panel;
    }

    private JLabel getLabel(String name, long count) {
        return new JLabel(name + count);
    }

}
