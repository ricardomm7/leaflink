package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;
import pt.ipp.isep.dei.project.domain.ProgressStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The type Entry dto.
 */
public class EntryDto {

    private GreenSpaceDto greenSpaceDto;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private int duration;

    private LocalDate estimatedDate;

    private TeamDto assignedTeam;

    private List<VehicleDto> assignedVehicles;

    private ProgressStatus progressStatus;

    /**
     * Instantiates a new Entry dto.
     *
     * @param greenSpaceDto   the green space dto
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public EntryDto(GreenSpaceDto greenSpaceDto, String description, DegreeOfUrgency degreeOfUrgency, int duration) {
        this.greenSpaceDto = greenSpaceDto;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
    }

    /**
     * Instantiates a new EntryDto to Agenda;
     *
     * @param greenSpace
     * @param description
     * @param degreeOfUrgency
     * @param duration
     * @param assignedTeam
     * @param assignedVehicles
     * @param progressStatus
     */
    public EntryDto(GreenSpaceDto greenSpace, String description, DegreeOfUrgency degreeOfUrgency, int duration, LocalDate estimatedDate, TeamDto assignedTeam, List<VehicleDto> assignedVehicles, ProgressStatus progressStatus) {
        this.greenSpaceDto = greenSpace;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
        this.estimatedDate = estimatedDate;
        this.assignedTeam = assignedTeam;
        this.assignedVehicles = assignedVehicles;
        this.progressStatus = progressStatus;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public ProgressStatus getStatus() {
        return progressStatus;
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
