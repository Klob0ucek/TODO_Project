package cz.muni.fi.pv168.todo.project.model;

import java.util.Objects;

public class Interval {
    private String name;
    private String abbreviation;
    private Integer duration;

    public Interval(
            String name,
            String abbreviation,
            Integer duration
    ) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(
            String name
    ) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(
            String abbreviation
    ) {
        this.abbreviation = abbreviation;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(
            Integer duration
    ) {
        this.duration = duration;
    }

    @Override
    public boolean equals(
            Object o
    ) {
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
