package cz.muni.fi.pv168.project.todoapp.business.model;

import org.junit.jupiter.api.Test;

public class TemplateIdentityTest {

    /**
     * This is regression test.
     * Two templates are same if they have their guid same.
     * Guid is auto-generated.
     */
    @Test
    void sameValuesDifferentGuid() {
        Template template1 = new Template("template1", false, "event", null, "location", null, null);
        Template template2 = new Template("template1", false, "event", null, "location", null, null);

        assert !template1.equals(template2);
    }

    /**
     * This is regression test.
     * Two templates are same if they have their guid same.
     */
    @Test
    void sameGuidDifferentValues() {
        Template template1 = new Template("guid", "template1", false, "event", null, "location", null, null);
        Template template2 = new Template("guid", "template2", true, "evening", null, "pub", null, null);

        assert template1.equals(template2);
    }
}
