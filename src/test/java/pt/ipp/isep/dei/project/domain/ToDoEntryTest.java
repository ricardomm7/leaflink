package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoEntryTest {

    private ToDoEntry toDoEntry;

    @BeforeEach
    public void setUp() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("Low");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));
    }

    @Test
    public void testGetTitle() {
        toDoEntry.setTitle("Gardening");
        assertEquals("Gardening", toDoEntry.getTitle());
    }

    @Test
    public void testGetDescription() {
        toDoEntry.setDescription("Gardening the backyard");
        assertEquals("Gardening the backyard", toDoEntry.getDescription());
    }

    @Test
    public void testGetDuration() {
        toDoEntry.setDuration(2);
        assertEquals(2, toDoEntry.getDuration());
    }

    @Test
    public void testGetUrgencyStatus() {
        toDoEntry.setDegreeOfUrgency(UrgencyStatus.valueOf("Low"));
        assertEquals(UrgencyStatus.valueOf("Low"), toDoEntry.getUrgencyStatus());
    }

    @Test
    public void testGetGreenSpace() {
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));
        toDoEntry.setGreenSpace(gs);
        assertEquals(gs, toDoEntry.getGreenSpace());
    }

    @Test
    public void testSetName_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setTitle("###Gardening$&"));
    }

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setTitle(""));
    }

    @Test
    public void testSetDescription_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDescription("###Gardening$&"));
    }

    @Test
    public void testSetDescription_EmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDescription(""));
    }

    @Test
    public void testSetDuration_NegativeDuration() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDuration(-1));
    }
}