package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AgendaEntryDto extends ToDoEntryDto implements Serializable {
    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpaceDto greenSpace;
    private LocalDate startingDate;
    private ProgressStatus progressStatus;
    private TeamDto assignedTeam;
    private List<VehicleDto> assignedVehicles;

    public AgendaEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace, LocalDate startingDate, ProgressStatus progressStatus, TeamDto assignedTeam, List<VehicleDto> assignedVehicles) {
        super(title, description, duration,urgencyStatus, greenSpace);
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;
        this.assignedTeam = assignedTeam;
        this.assignedVehicles = assignedVehicles;
    }

        public AgendaEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace, LocalDate startingDate, ProgressStatus progressStatus) {
        super(title, description, duration,urgencyStatus, greenSpace);
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;

    }


    public TeamDto getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(TeamDto assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public List<VehicleDto> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void setAssignedVehicles(List<VehicleDto> assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
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

    public GreenSpaceDto getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpaceDto greenSpace) {
        this.greenSpace = greenSpace;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    public void setUrgencyStatus(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }
}