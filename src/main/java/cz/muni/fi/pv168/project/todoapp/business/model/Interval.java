package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;
import java.util.Objects;

public class Interval extends Entity {
    private String name;
    private String abbreviation;
    private Duration duration;

    public Interval(
            String name,
            String abbreviation,
            Duration duration
    ) {
        super();
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
        super(guid);
        this.name = name;
        this.abbreviation = abbreviation;
        this.duration = duration;
    }

    public Interval() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Objects.equals(name, interval.name)
                && Objects.equals(abbreviation, interval.abbreviation)
                && Objects.equals(duration, interval.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, abbreviation, duration);
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
