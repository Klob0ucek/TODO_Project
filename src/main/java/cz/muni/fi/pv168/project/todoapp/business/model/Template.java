package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Template extends AbstractCoreEvent {
    String templateName;

    public Template() {
    }

    public Template(String templateName,
                    boolean isDone,
                    String eventName,
                    List<Category> categories,
                    String location,
                    LocalTime time,
                    Duration duration) {
        super(isDone, eventName, categories, location, time, duration);
        this.templateName = templateName;
    }

    public Template(String guid,
                    String templateName,
                    boolean isDone,
                    String eventName,
                    List<Category> categories,
                    String location,
                    LocalTime time,
                    Duration duration) {
        super(guid, isDone, eventName, categories, location, time, duration);
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
