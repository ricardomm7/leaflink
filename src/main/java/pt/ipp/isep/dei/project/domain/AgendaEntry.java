package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AgendaEntry extends ToDoEntry implements Serializable {
    private LocalDate startingDate;
    private ProgressStatus progressStatus;
    private Team assignedTeam;
    private List<Vehicle> assignedVehicles;


    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public List<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void setAssignedVehicles(List<Vehicle>assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public AgendaEntry(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpace greenSpace,
                       LocalDate startingDate, ProgressStatus progressStatus) {
        super(title, description, duration, urgencyStatus, greenSpace);
        this.startingDate = startingDate;
        this.progressStatus = progressStatus;
    }


}
