package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.UrgencyStatus;

import java.util.Objects;

/**
 * The ToDoEntryDto class represents a data transfer object for the ToDoEntry domain object.
 * It encapsulates the data related to a to-do entry and provides methods to access and manipulate this data.
 */
public class ToDoEntryDto {
    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpaceDto greenSpace;

    /**
     * Constructs a new ToDoEntryDto object with the provided data.
     *
     * @param title         the title of the to-do entry
     * @param description   the description of the to-do entry
     * @param duration      the duration of the to-do entry
     * @param urgencyStatus the urgency status of the to-do entry
     * @param greenSpace    the green space associated with the to-do entry
     */
    public ToDoEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.urgencyStatus = urgencyStatus;
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the description of the to-do entry.
     *
     * @return the description of the to-do entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the to-do entry.
     *
     * @param description the description of the to-do entry
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the duration of the to-do entry.
     *
     * @return the duration of the to-do entry
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the to-do entry.
     *
     * @param duration the duration of the to-do entry
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the green space associated with the to-do entry.
     *
     * @return the green space associated with the to-do entry
     */
    public GreenSpaceDto getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the green space associated with the to-do entry.
     *
     * @param greenSpace the green space to be associated with the to-do entry
     */
    public void setGreenSpace(GreenSpaceDto greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the title of the to-do entry.
     *
     * @return the title of the to-do entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the to-do entry.
     *
     * @param title the title of the to-do entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the urgency status of the to-do entry.
     *
     * @return the urgency status of the to-do entry
     */
    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    /**
     * Sets the urgency status of the to-do entry.
     *
     * @param urgencyStatus the urgency status of the to-do entry
     */
    public void setUrgencyStatus(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }
        /**
     * Checks if two to-do entries are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoEntryDto toDoEntry = (ToDoEntryDto) o;
        return duration == toDoEntry.duration &&
                Objects.equals(greenSpace, toDoEntry.greenSpace) &&
                Objects.equals(description, toDoEntry.description) &&
                urgencyStatus == toDoEntry.urgencyStatus;
    }
}
