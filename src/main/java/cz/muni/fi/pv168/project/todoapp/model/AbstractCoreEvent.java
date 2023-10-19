package cz.muni.fi.pv168.project.todoapp.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCoreEvent {
    protected boolean isDone;
    protected String name;
    protected List<Category> categories;
    protected String location;
    protected LocalTime time;
    protected Duration duration;

    protected AbstractCoreEvent(boolean isDone, String name, List<Category> categories,
                                String location, LocalTime time, Duration duration) {
        this.isDone = isDone;
        this.name = name;
        this.categories = categories;
        this.location = location;
        this.time = time;
        this.duration = duration;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCoreEvent that)) return false;

        if (isDone != that.isDone) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(categories, that.categories)) return false;
        if (!Objects.equals(location, that.location)) return false;
        if (!Objects.equals(time, that.time)) return false;
        return Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        int result = (isDone ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
