package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testToDoEntryConstructorValidInput() {
        // Arrange
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("Low");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));

        // Act
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        // Assert
        assertEquals(title, entry.getTitle());
        assertEquals(description, entry.getDescription());
        assertEquals(duration, entry.getDuration());
        assertEquals(urgencyStatus, entry.getUrgencyStatus());
        assertEquals(gs, entry.getGreenSpace());
    }

    @Test
    public void testToDoEntryConstructorInvalidInput() {
        // Arrange
        String title = null; // Invalid title
        String description = null; // Invalid description
        int duration = -1; // Invalid duration
        UrgencyStatus urgencyStatus = UrgencyStatus.HIGH;
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));


        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new ToDoEntry(title, description, duration, urgencyStatus, gs);
        });

        assertEquals("Error when setting the To-Do Entry attributes.", exception.getMessage());
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


}