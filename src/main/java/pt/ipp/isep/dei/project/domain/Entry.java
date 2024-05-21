package pt.ipp.isep.dei.project.domain;

import java.util.Objects;

/**
 * The type Entry.
 */
public class Entry {

    private GreenSpace greenSpace;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private String duration;


    /**
     * Instantiates a new Entry.
     *
     * @param greenSpace      the green space
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public Entry(GreenSpace greenSpace, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
        this.greenSpace = greenSpace;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
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
        this.greenSpace = greenSpace;
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
        this.description = description;
    }

    /**
     * Gets degree of urgency.
     *
     * @return the degree of urgency
     */
    public DegreeOfUrgency getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    /**
     * Sets degree of urgency.
     *
     * @param degreeOfUrgency the degree of urgency
     */
    public void setDegreeOfUrgency(DegreeOfUrgency degreeOfUrgency) {
        this.degreeOfUrgency = degreeOfUrgency;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(greenSpace, entry.greenSpace) && Objects.equals(description, entry.description) && degreeOfUrgency == entry.degreeOfUrgency && Objects.equals(duration, entry.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greenSpace, description, degreeOfUrgency, duration);
    }

    @Override
    public String toString() {
        return "Entry" +
                "Green Space= " + greenSpace +
                "Description= " + description +
                "Degree of Urgency= " + degreeOfUrgency +
                "Duration= " + duration;
    }
}
