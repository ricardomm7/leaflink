package pt.ipp.isep.dei.project.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The type Entry.
 */
public class Entry {

    private GreenSpace greenSpace;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private int duration;

    private LocalDate estimatedDate;

    private Team assignedTeam;

    private List<Vehicle> assignedVehicles;

    private Status status;


    /**
     * Instantiates a new Entry to ToDoList.
     *
     * @param greenSpace      the green space
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public Entry(GreenSpace greenSpace, String description, DegreeOfUrgency degreeOfUrgency, int duration) {
        this.greenSpace = greenSpace;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
    }

    /**
     * Instantiates a new Entry to Agenda;
     *
     * @param greenSpace
     * @param description
     * @param degreeOfUrgency
     * @param duration
     * @param assignedTeam
     * @param assignedVehicles
     * @param status
     */
    public Entry(GreenSpace greenSpace, String description, DegreeOfUrgency degreeOfUrgency, int duration, LocalDate estimatedDate, Team assignedTeam, List<Vehicle> assignedVehicles, Status status) {
        this.greenSpace = greenSpace;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
        this.estimatedDate = estimatedDate;
        this.assignedTeam = assignedTeam;
        this.assignedVehicles = assignedVehicles;
        this.status = status;
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
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets estimated date.
     *
     * @return the estimated date
     */
    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    /**
     * Sets estimated date.
     *
     * @param estimatedDate the estimated date
     */
    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
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
    public int getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
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
