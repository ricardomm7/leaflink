package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.Objects;

/**
 * The ToDoEntry class represents an entry in a to-do list.
 */
public class ToDoEntry implements Serializable {

    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpace greenSpace;

    /**
     * Constructs a to-do entry with the given attributes.
     *
     * @param title         The title of the to-do entry.
     * @param description   The description of the to-do entry.
     * @param duration      The duration of the to-do entry.
     * @param urgencyStatus The urgency status of the to-do entry.
     * @param greenSpace    The green space associated with the to-do entry.
     */
    public ToDoEntry(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpace greenSpace) {
       try {
           setTitle(title);
           setDescription(description);
           setDuration(duration);
           setDegreeOfUrgency(urgencyStatus);
           setGreenSpace(greenSpace);
       } catch (Exception e) {
           ShowError.showAlert("To-Do Entry", e.getMessage(), "Error when setting the To-Do Entry attributes.");
       }
    }

    /**
     * Gets the title of the to-do entry.
     *
     * @return The title of the to-do entry.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the to-do entry.
     *
     * @param title The title of the to-do entry.
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalStateException("Title cannot be null");
        }
        // Check for special characters (assuming alphanumeric and spaces are valid)
        if (!title.matches("^[\\p{Alnum}\\s]*$")) {
            throw new IllegalArgumentException("Title cannot contain special characters");
        }
        this.title = title;
    }

    /**
     * Gets the description of the to-do entry.
     *
     * @return The description of the to-do entry.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the to-do entry.
     *
     * @param description The description of the to-do entry.
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalStateException("Description cannot be null");
        }
        // Check for special characters (assuming alphanumeric and spaces are valid)
        if (!description.matches("^[\\p{Alnum}\\s]*$")) {
            throw new IllegalArgumentException("Description cannot contain special characters");
        }
        this.description = description;
    }

    /**
     * Gets the duration of the to-do entry.
     *
     * @return The duration of the to-do entry.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the to-do entry.
     *
     * @param duration The duration of the to-do entry.
     */
    public void setDuration(int duration) throws IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number");
        }
        this.duration = duration;
    }

    /**
     * Gets the urgency status of the to-do entry.
     *
     * @return The urgency status of the to-do entry.
     */
    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    /**
     * Sets the urgency status of the to-do entry.
     *
     * @param urgencyStatus The urgency status of the to-do entry.
     */
    public void setDegreeOfUrgency(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }

    /**
     * Gets the green space associated with the to-do entry.
     *
     * @return The green space associated with the to-do entry.
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the green space associated with the to-do entry.
     *
     * @param greenSpace The green space associated with the to-do entry.
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
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
        ToDoEntry toDoEntry = (ToDoEntry) o;
        return duration == toDoEntry.duration &&
                Objects.equals(greenSpace, toDoEntry.greenSpace) &&
                Objects.equals(description, toDoEntry.description) &&
                urgencyStatus == toDoEntry.urgencyStatus;
    }

    /**
     * Generates a hash code for the to-do entry.
     *
     * @return The hash code for the to-do entry.
     */
    @Override
    public int hashCode() {
        return Objects.hash(greenSpace, description, urgencyStatus, duration);
    }

    /**
     * Returns a string representation of the to-do entry.
     *
     * @return A string representation of the to-do entry.
     */
    @Override
    public String toString() {
        return "ToDoEntry{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", urgencyStatus=" + urgencyStatus +
                ", greenSpace=" + greenSpace +
                '}';
    }
}
