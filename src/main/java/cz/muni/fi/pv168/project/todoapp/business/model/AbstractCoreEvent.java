package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public abstract class AbstractCoreEvent extends Entity {
    protected boolean isDone;
    protected String name;
    protected List<Category> categories;
    protected String location;
    protected LocalTime time;
    protected Duration duration;

    protected AbstractCoreEvent(boolean isDone, String name, List<Category> categories,
                                String location, LocalTime time, Duration duration) {
        super(UniqueIdProvider.newId());
        this.isDone = isDone;
        this.name = name;
        this.categories = categories;
        this.location = location;
        this.time = time;
        this.duration = duration;
    }

    protected AbstractCoreEvent(String guid, boolean isDone, String name, List<Category> categories,
                                String location, LocalTime time, Duration duration) {
        this(isDone, name, categories, location, time, duration);
        this.guid = guid;
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
}
