package cz.muni.fi.pv168.project.todoapp.business.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Template extends AbstractCoreEvent {
    String templateName;

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

    public Template() {
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template template)) return false;
        if (!super.equals(o)) return false;

        return templateName.equals(template.templateName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + templateName.hashCode();
        return result;
    }
}
