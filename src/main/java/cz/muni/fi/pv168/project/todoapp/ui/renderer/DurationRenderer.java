package cz.muni.fi.pv168.todo.project.ui.renderer;


import java.time.Duration;

public class DurationRenderer {

    public static String renderDuration(Duration duration) {
        return String.valueOf(duration.toMinutes()) + " min";
    }


}
