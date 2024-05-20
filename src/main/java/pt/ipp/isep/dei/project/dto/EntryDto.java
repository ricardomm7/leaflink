package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;

import java.util.Objects;

public class EntryDto {

   private GreenSpaceDto greenSpaceDto;

   private String description;

   private DegreeOfUrgency degreeOfUrgency;

   private String duration;

    public EntryDto(GreenSpaceDto greenSpaceDto, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
        this.greenSpaceDto = greenSpaceDto;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.duration = duration;
    }

    public GreenSpaceDto getGreenSpaceDto() {
        return greenSpaceDto;
    }

    public void setGreenSpaceDto(GreenSpaceDto greenSpaceDto) {
        this.greenSpaceDto = greenSpaceDto;
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
        EntryDto entryDto = (EntryDto) o;
        return Objects.equals(greenSpaceDto, entryDto.greenSpaceDto) && Objects.equals(description, entryDto.description) && degreeOfUrgency == entryDto.degreeOfUrgency && Objects.equals(duration, entryDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greenSpaceDto, description, degreeOfUrgency, duration);
    }

    @Override
    public String toString() {
        return "EntryDto{" +
                "greenSpaceDto=" + greenSpaceDto +
                ", description='" + description + '\'' +
                ", degreeOfUrgency=" + degreeOfUrgency +
                ", duration='" + duration + '\'' +
                '}';
    }
}
