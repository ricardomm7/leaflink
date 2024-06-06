package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type To do entry test.
 */
public class ToDoEntryTest {
    @Test
    public void testSetTitle_ValidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        String newTitle = "Mowing the lawn";
        entry.setTitle(newTitle);

        assertEquals(newTitle, entry.getTitle());
    }


    @Test
    public void testSetDescription_ValidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        String newDescription = "Trimming the hedges";
        entry.setDescription(newDescription);

        assertEquals(newDescription, entry.getDescription());
    }

    @Test
    public void testSetDescription_InvalidCharacters() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        assertThrows(IllegalArgumentException.class, () -> entry.setDescription("###Gardening$&"));
    }

    @Test
    public void testSetDuration_ValidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        int newDuration = 4;
        entry.setDuration(newDuration);

        assertEquals(newDuration, entry.getDuration());
    }

    @Test
    public void testSetDuration_InvalidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        assertThrows(IllegalArgumentException.class, () -> entry.setDuration(-1));
    }

    @Test
    public void testSetUrgencyStatus_ValidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        UrgencyStatus newUrgencyStatus = UrgencyStatus.HIGH;
        entry.setDegreeOfUrgency(newUrgencyStatus);

        assertEquals(newUrgencyStatus, entry.getUrgencyStatus());
    }

    @Test
    public void testSetGreenSpace_ValidInput() {
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("LOW");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "Porto", "4000-007"));
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        GreenSpace newGreenSpace = new GreenSpace("Park", GreenSpaceType.GARDEN, 20, "b@b.com", new Address("Avenida", "Porto", "4000-008"));
        entry.setGreenSpace(newGreenSpace);

        assertEquals(newGreenSpace, entry.getGreenSpace());
    }
}