package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobTest {

    @Test
    public void testSetTitle_ValidTitle() {
        String validTitle = "Software Engineer";
        Job job = new Job(validTitle);
        assertEquals(validTitle, job.getTitle());
    }

    @Test
    public void testSetTitle_InvalidTitle_Empty() {
        assertThrows(NoClassDefFoundError.class, () -> new Job(""));
    }

    @Test
    public void testSetTitle_InvalidTitle_Whitespace() {
        assertThrows(NoClassDefFoundError.class, () -> new Job("   "));
    }

    @Test
    public void testSetTitle_InvalidTitle_SpecialCharacters() {
        assertThrows(NoClassDefFoundError.class, () -> new Job("Software Engineer!"));
    }
}
