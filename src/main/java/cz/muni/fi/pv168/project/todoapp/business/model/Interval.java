package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;

public class Interval extends Entity {
    private String name;
    private String abbreviation;
    private Duration duration;

    public Interval() {
    }

    public Interval(
            String name,
            String abbreviation,
            Duration duration
    ) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.duration = duration;
    }

    public Interval(
            String guid,
            String name,
            String abbreviation,
            Duration duration
    ) {
        this(name, abbreviation, duration);
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(
            Duration duration
    ) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", duration=" + duration +
                '}';
    }
}
