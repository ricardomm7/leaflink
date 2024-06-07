package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * The AgendaEntryDto class represents a data transfer object for the AgendaEntry domain object.
 * It encapsulates the data related to an agenda entry and provides methods to access and manipulate this data.
 * This class extends the ToDoEntryDto class and adds additional properties specific to an agenda entry.
 */
public class AgendaEntryDto implements Serializable {
    public String title;
    public String description;
    public int duration;
    public UrgencyStatus urgencyStatus;
    public GreenSpaceDto greenSpace;
    public LocalDate startingDate;
    public ProgressStatus progressStatus;
    public TeamDto assignedTeam;
    public List<VehicleDto> assignedVehicles;

    /**
     * Constructs a new AgendaEntryDto object with the provided data, including the assigned team and vehicles.
     *
     * @param title            the title of the agenda entry
     * @param description      the description of the agenda entry
     * @param duration         the duration of the agenda entry
     * @param urgencyStatus    the urgency status of the agenda entry
     * @param greenSpace       the green space associated with the agenda entry
     * @param startingDate     the starting date of the agenda entry
     * @param progressStatus   the progress status of the agenda entry
     * @param assignedTeam     the team assigned to the agenda entry
     * @param assignedVehicles the list of vehicles assigned to the agenda entry
     */
    public AgendaEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace, LocalDate startingDate, ProgressStatus progressStatus, TeamDto assignedTeam, List<VehicleDto> assignedVehicles) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.urgencyStatus = urgencyStatus;
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;
        this.assignedTeam = assignedTeam;
        this.assignedVehicles = assignedVehicles;
    }

    /**
     * Constructs a new AgendaEntryDto object with the provided data, without the assigned team and vehicles.
     *
     * @param title          the title of the agenda entry
     * @param description    the description of the agenda entry
     * @param duration       the duration of the agenda entry
     * @param urgencyStatus  the urgency status of the agenda entry
     * @param greenSpace     the green space associated with the agenda entry
     * @param startingDate   the starting date of the agenda entry
     * @param progressStatus the progress status of the agenda entry
     */
    public AgendaEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace, LocalDate startingDate, ProgressStatus progressStatus) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.urgencyStatus = urgencyStatus;
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;
        this.assignedTeam = null;
        this.assignedVehicles = null;
    }

    /**
     * Gets the team assigned to the agenda entry.
     *
     * @return the team assigned to the agenda entry
     */
    public TeamDto getAssignedTeam() {
        return assignedTeam;
    }

    /**
     * Sets the team assigned to the agenda entry.
     *
     * @param assignedTeam the team to be assigned to the agenda entry
     */
    public void setAssignedTeam(TeamDto assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    /**
     * Gets the list of vehicles assigned to the agenda entry.
     *
     * @return the list of vehicles assigned to the agenda entry
     */
    public List<VehicleDto> getAssignedVehicles() {
        return assignedVehicles;
    }

    /**
     * Sets the list of vehicles assigned to the agenda entry.
     *
     * @param assignedVehicles the list of vehicles to be assigned to the agenda entry
     */
    public void setAssignedVehicles(List<VehicleDto> assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
    }

    /**
     * Gets the description of the agenda entry.
     *
     * @return the description of the agenda entry
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the agenda entry.
     *
     * @param description the description of the agenda entry
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the duration of the agenda entry.
     *
     * @return the duration of the agenda entry
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Sets the duration of the agenda entry.
     *
     * @param duration the duration of the agenda entry
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the green space associated with the agenda entry.
     *
     * @return the green space associated with the agenda entry
     */
    public GreenSpaceDto getGreenSpace() {
        return this.greenSpace;
    }

    /**
     * Sets the green space associated with the agenda entry.
     *
     * @param greenSpace the green space to be associated with the agenda entry
     */
    public void setGreenSpace(GreenSpaceDto greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the progress status of the agenda entry.
     *
     * @return the progress status of the agenda entry
     */
    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    /**
     * Sets the progress status of the agenda entry.
     *
     * @param progressStatus the progress status of the agenda entry
     */
    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    /**
     * Gets the starting date of the agenda entry.
     *
     * @return the starting date of the agenda entry
     */
    public LocalDate getStartingDate() {
        return startingDate;
    }

    /**
     * Sets the starting date of the agenda entry.
     *
     * @param startingDate the starting date of the agenda entry
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Gets the title of the agenda entry.
     *
     * @return the title of the agenda entry
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of the agenda entry.
     *
     * @param title the title of the agenda entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the urgency status of the agenda entry.
     *
     * @return the urgency status of the agenda entry
     */
    public UrgencyStatus getUrgencyStatus() {
        return this.urgencyStatus;
    }


    /**
     * Sets the urgency status of the agenda entry.
     *
     * @param urgencyStatus the urgency status of the agenda entry
     */
    public void setUrgencyStatus(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }

    @Override
    public String toString() {
        return this.title + " " + description;
    }



}
