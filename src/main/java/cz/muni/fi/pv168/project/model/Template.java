package cz.muni.fi.pv168.project.model;

import java.time.LocalTime;
import java.util.List;

public class Template extends AbstractCoreEvent{
    String templateName;

    public Template(String templateName,
                    boolean isDone,
                    String eventName,
                    List<Category> categories,
                    String location,
                    LocalTime time,
                    LocalTime duration) {
        super(isDone, eventName, categories, location, time, duration);
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
