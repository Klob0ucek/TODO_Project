package cz.muni.fi.pv168.project.todoapp.business.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventIdentityTest {

    /**
     * This is regression test.
     * Two events are same if they have their guid same.
     * Guid is auto-generated.
     */
    @Test
    void sameValuesDifferentGuid() {
        Category category = new Category("Sport", CategoryColor.YELLOW);
        Event event1 = new Event(false, "event1", List.of(category), "pepepizza",
                LocalDate.of(2023, 10, 10), LocalTime.of(12, 0), Duration.ofMinutes(15));
        Event event2 = new Event(false, "event1", List.of(category), "pepepizza",
                LocalDate.of(2023, 10, 10), LocalTime.of(12, 0), Duration.ofMinutes(15));

        assert !event1.equals(event2);
    }

    /**
     * This is regression test.
     * Two events are same if they have their guid same.
     */
    @Test
    void sameGuidDifferentValues() {
        Category category = new Category("Sport", CategoryColor.YELLOW);
        Event event1 = new Event("guid", false, "event1", List.of(category), "pepepizza",
                LocalDate.of(2023, 10, 10), LocalTime.of(12, 0), Duration.ofMinutes(15));
        Event event2 = new Event("guid", true, "eventDifferent", List.of(category), "luigi",
                LocalDate.of(2023, 11, 10), LocalTime.of(12, 0), Duration.ofMinutes(15));

        assert event1.equals(event2);
    }

}
