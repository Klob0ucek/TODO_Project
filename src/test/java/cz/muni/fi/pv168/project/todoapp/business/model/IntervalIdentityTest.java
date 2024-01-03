package cz.muni.fi.pv168.project.todoapp.business.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

public class IntervalIdentityTest {

    /**
     * This is regression test.
     * Two intervals are same if they have their guid same.
     * Guid is auto-generated.
     */
    @Test
    void sameValuesDifferentGuid() {
        Interval interval1 = new Interval("name", "ab", Duration.ofMinutes(1));
        Interval interval2 = new Interval("name", "ab", Duration.ofMinutes(1));

        assert !interval1.equals(interval2);
    }

    /**
     * This is regression test.
     * Two intervals are same if they have their guid same.
     */
    @Test
    void sameGuidDifferentValues() {
        Interval interval1 = new Interval("guid", "name", "ab", Duration.ofMinutes(1));
        Interval interval2 = new Interval("guid", "fame", "fab", Duration.ofMinutes(100));

        assert interval1.equals(interval2);
    }
}
