package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type To do entry test.
 */
public class ToDoEntryTest {

    private ToDoEntry toDoEntry;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("Low");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));
    }

    /**
     * Test get title.
     */
    @Test
    public void testGetTitle() {
        toDoEntry.setTitle("Gardening");
        assertEquals("Gardening", toDoEntry.getTitle());
    }

    /**
     * Test get description.
     */
    @Test
    public void testGetDescription() {
        toDoEntry.setDescription("Gardening the backyard");
        assertEquals("Gardening the backyard", toDoEntry.getDescription());
    }

    /**
     * Test get duration.
     */
    @Test
    public void testGetDuration() {
        toDoEntry.setDuration(2);
        assertEquals(2, toDoEntry.getDuration());
    }

    /**
     * Test get urgency status.
     */
    @Test
    public void testGetUrgencyStatus() {
        toDoEntry.setDegreeOfUrgency(UrgencyStatus.valueOf("Low"));
        assertEquals(UrgencyStatus.valueOf("Low"), toDoEntry.getUrgencyStatus());
    }

    /**
     * Test get green space.
     */
    @Test
    public void testGetGreenSpace() {
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));
        toDoEntry.setGreenSpace(gs);
        assertEquals(gs, toDoEntry.getGreenSpace());
    }

    /**
     * Test set name invalid characters.
     */
    @Test
    public void testSetName_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setTitle("###Gardening$&"));
    }

    /**
     * Test set name empty name.
     */
    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setTitle(""));
    }

    /**
     * Test set description invalid characters.
     */
    @Test
    public void testSetDescription_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDescription("###Gardening$&"));
    }

    /**
     * Test set description empty description.
     */
    @Test
    public void testSetDescription_EmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDescription(""));
    }

    /**
     * Test set duration negative duration.
     */
    @Test
    public void testSetDuration_NegativeDuration() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDuration(-1));
    }
}