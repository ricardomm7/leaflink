package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type To do entry.
 */
public class ToDoEntry implements Serializable {

    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpace greenSpace;

    /**
     * Instantiates a new To do entry.
     *
     * @param title         the title
     * @param description   the description
     * @param duration      the duration
     * @param urgencyStatus the urgency status
     * @param greenSpace    the green space
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
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
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void setDuration(int duration) throws IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number");
        }
        this.duration = duration;
    }

    /**
     * Gets urgency status.
     *
     * @return the urgency status
     */
    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    /**
     * Sets degree of urgency.
     *
     * @param urgencyStatus the urgency status
     */
    public void setDegreeOfUrgency(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }

    /**
     * Gets green space.
     *
     * @return the green space
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets green space.
     *
     * @param greenSpace the green space
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        if (greenSpace != null) {
            this.greenSpace = greenSpace;
        } else {
            throw new IllegalArgumentException("Green Space cannot be null");
        }
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(greenSpace, description, urgencyStatus, duration);
    }

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
