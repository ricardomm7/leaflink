package pt.ipp.isep.dei.project.domain;

import java.util.Objects;

public class Entry {

    private GreenSpace greenSpace;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private String duration;


    public Entry(GreenSpace greenSpace, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
        this.greenSpace = greenSpace;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DegreeOfUrgency getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    public void setDegreeOfUrgency(DegreeOfUrgency degreeOfUrgency) {
        this.degreeOfUrgency = degreeOfUrgency;
    }

    public String getDuration() {
        return duration;
    }

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
        return "Entry{" +
                "greenSpace=" + greenSpace +
                ", description='" + description + '\'' +
                ", degreeOfUrgency=" + degreeOfUrgency +
                ", duration='" + duration + '\'' +
                '}';
    }
}
