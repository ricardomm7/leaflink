package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The AgendaEntry class represents a scheduled task or entry that extends ToDoEntry.
 * It includes additional details such as the starting date, progress status, assigned team, and assigned vehicles.
 */
public class AgendaEntry extends ToDoEntry implements Serializable {

    private LocalDate startingDate;
    private ProgressStatus progressStatus;
    private Team assignedTeam;
    private List<Vehicle> assignedVehicles = new ArrayList<>();

    /**
     * Constructs a new AgendaEntry with the specified details.
     *
     * @param title          the title of the AgendaEntry.
     * @param description    the description of the AgendaEntry.
     * @param duration       the duration of the AgendaEntry in hours.
     * @param urgencyStatus  the urgency status of the AgendaEntry.
     * @param greenSpace     the GreenSpace associated with the AgendaEntry.
     * @param startingDate   the starting date of the AgendaEntry.
     * @param progressStatus the progress status of the AgendaEntry.
     */
    public AgendaEntry(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpace greenSpace,
                       LocalDate startingDate, ProgressStatus progressStatus) {
        super(title, description, duration, urgencyStatus, greenSpace);
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;
    }

    /**
     * Constructs a new AgendaEntry by copying an existing one and setting a new date and progress status.
     *
     * @param agendaEntry the existing AgendaEntry to copy.
     * @param newDate     the new starting date for the copied AgendaEntry.
     * @param PLANNED     the new progress status for the copied AgendaEntry.
     */
    public AgendaEntry(AgendaEntry agendaEntry, LocalDate newDate, ProgressStatus PLANNED) {
        super(agendaEntry.getTitle(), agendaEntry.getDescription(), agendaEntry.getDuration(), agendaEntry.getUrgencyStatus(), agendaEntry.getGreenSpace());
        this.startingDate = newDate;
        this.progressStatus = PLANNED;
    }

    public AgendaEntry(ToDoEntry agendaEntry, LocalDate newDate, ProgressStatus PLANNED) {
        super(agendaEntry.getTitle(), agendaEntry.getDescription(), agendaEntry.getDuration(), agendaEntry.getUrgencyStatus(), agendaEntry.getGreenSpace());
        this.startingDate = newDate;
        this.progressStatus = PLANNED;
    }

    /**
     * Gets the starting date of the AgendaEntry.
     *
     * @return the starting date of the AgendaEntry.
     */
    public LocalDate getStartingDate() {
        return startingDate;
    }

    /**
     * Sets the starting date of the AgendaEntry.
     *
     * @param startingDate the new starting date of the AgendaEntry.
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Gets the progress status of the AgendaEntry.
     *
     * @return the progress status of the AgendaEntry.
     */
    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    /**
     * Sets the progress status of the AgendaEntry.
     *
     * @param progressStatus the new progress status of the AgendaEntry.
     */
    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    /**
     * Gets the team assigned to the AgendaEntry.
     *
     * @return the assigned team.
     */
    public Team getAssignedTeam() {
        return assignedTeam;
    }

    /**
     * Sets the team assigned to the AgendaEntry.
     *
     * @param assignedTeam the new assigned team.
     */
    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    /**
     * Gets the list of vehicles assigned to the AgendaEntry.
     *
     * @return the list of assigned vehicles.
     */
    public List<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    /**
     * Sets the list of vehicles assigned to the AgendaEntry.
     *
     * @param assignedVehicles the new list of assigned vehicles.
     */
    public void setAssignedVehicles(List<Vehicle> assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
    }

    /**
     * Sets available collaborators of a team and vehicles assigned to a completed task.
     *
     * @param agendaEntry the agenda entry that has been completed.
     */
    public void setAvailable(AgendaEntry agendaEntry) {
        Team team = agendaEntry.getAssignedTeam();
        if (team != null) {
            team.setAvailable(true);

            for (Collaborator collaborator : team.getCollaborators()) {
                collaborator.setAvailable(true);
            }
            List<Vehicle> vehicleList = agendaEntry.getAssignedVehicles();
            for (Vehicle vehicle : vehicleList) {
                vehicle.setAvailable(true);
            }
        } else {
            ShowError.showAlert("Completion Error", "There is no Team assigned to this Entry.", "No team assigned");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Ensure equality checks with ToDoEntry class

        AgendaEntry that = (AgendaEntry) o;

        if (!startingDate.equals(that.startingDate)) return false;
        if (progressStatus != that.progressStatus) return false;
        if (!Objects.equals(assignedTeam, that.assignedTeam)) return false;
        return assignedVehicles.equals(that.assignedVehicles);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + startingDate.hashCode();
        result = 31 * result + (progressStatus != null ? progressStatus.hashCode() : 0);
        result = 31 * result + (assignedTeam != null ? assignedTeam.hashCode() : 0);
        result = 31 * result + assignedVehicles.hashCode();
        return result;
    }


}

