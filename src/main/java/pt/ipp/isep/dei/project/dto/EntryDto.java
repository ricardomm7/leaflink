package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;

import java.util.Objects;

/**
 * The type Entry dto.
 */
public class EntryDto {

    private GreenSpaceDto greenSpaceDto;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private String duration;

    /**
     * Instantiates a new Entry dto.
     *
     * @param greenSpaceDto   the green space dto
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public EntryDto(GreenSpaceDto greenSpaceDto, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
        this.greenSpaceDto = greenSpaceDto;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
    }

    /**
     * Gets green space dto.
     *
     * @return the green space dto
     */
    public GreenSpaceDto getGreenSpaceDto() {
        return greenSpaceDto;
    }

    /**
     * Sets green space dto.
     *
     * @param greenSpaceDto the green space dto
     */
    public void setGreenSpaceDto(GreenSpaceDto greenSpaceDto) {
        this.greenSpaceDto = greenSpaceDto;
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
        EntryDto entryDto = (EntryDto) o;
        return Objects.equals(greenSpaceDto, entryDto.greenSpaceDto) && Objects.equals(description, entryDto.description) && degreeOfUrgency == entryDto.degreeOfUrgency && Objects.equals(duration, entryDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greenSpaceDto, description, degreeOfUrgency, duration);
    }

    @Override
    public String toString() {
        return "Entry:" +
                "Green Space= " + greenSpaceDto +
                "Description= " + description +
                "Degree of Urgency= " + degreeOfUrgency +
                "Duration= " + duration;
    }
}
