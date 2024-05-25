package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Entry.
 */
public class ToDoEntry implements Serializable {

    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpace greenSpace;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    public void setDegreeOfUrgency(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Instantiates a new Entry to ToDoList.
     *
     * @param greenSpace    the green space
     * @param description   the description
     * @param urgencyStatus the degree of urgency
     * @param duration      the duration
     */
    public ToDoEntry(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpace greenSpace) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.urgencyStatus = urgencyStatus;
        this.greenSpace = greenSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoEntry toDoEntry = (ToDoEntry) o;
        return Objects.equals(greenSpace, toDoEntry.greenSpace) && Objects.equals(description, toDoEntry.description) && urgencyStatus == toDoEntry.urgencyStatus && Objects.equals(duration, toDoEntry.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greenSpace, description, urgencyStatus, duration);
    }

    @Override
    public String toString() {
        return "ToDoEntry" + "Green Space= " + greenSpace + "Description= " + description + "Degree of Urgency= " + urgencyStatus + "Duration= " + duration;
    }

}
