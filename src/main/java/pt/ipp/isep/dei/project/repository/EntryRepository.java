package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.controller.ListTaskController;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * The EntryRepository class manages the persistence of ToDoEntry and AgendaEntry objects.
 * It provides methods to create, update, and retrieve these entries.
 */
public class EntryRepository implements Serializable {

    /**
     * The To do entry list.
     */
    private final List<ToDoEntry> toDoEntryList;
    /**
     * The Agenda entry list.
     */
    private final List<AgendaEntry> agendaEntryList;

    /**
     * Constructs a new EntryRepository instance and initializes the internal lists.
     */
    public EntryRepository() {
        agendaEntryList = new ArrayList<>();
        toDoEntryList = new ArrayList<>();
    }

    /**
     * Creates a new ToDoEntry from the provided ToDoEntryDto.
     *
     * @param dto the ToDoEntryDto containing the information for the new ToDoEntry.
     * @throws IllegalArgumentException if a ToDoEntry with the same description already exists.
     */
    public void createNewToDoEntry(ToDoEntry dto) {
        if (checkForDuplicates(dto)) {
            addToDoEntry(dto);
        } else {
            throw new IllegalArgumentException("There is already a ToDoEntry with the same description.");
        }
    }

    /**
     * Checks for duplicates in the toDoEntryList.
     *
     * @param toDoEntry the ToDoEntry to check for duplicates.
     * @return true if no duplicates are found, false otherwise.
     */
    private boolean checkForDuplicates(ToDoEntry toDoEntry) {
        return toDoEntryList.stream()
                .noneMatch(e -> e.getDescription().equalsIgnoreCase(toDoEntry.getDescription()));
    }

    /**
     * Adds a new ToDoEntry to the toDoEntryList.
     *
     * @param toDoEntry the ToDoEntry to add.
     */
    private void addToDoEntry(ToDoEntry toDoEntry) {
        toDoEntryList.add(toDoEntry);
    }

    /**
     * Add agenda entry.
     *
     * @param agendaEntry the agenda entry
     */
    public void addAgendaEntry(AgendaEntry agendaEntry) {
        agendaEntryList.add(agendaEntry);
    }

    /**
     * Retrieves a list of AgendaEntryDto objects managed by the specified Green Space Manager (GSM).
     *
     * @param GSM the UserSession of the Green Space Manager.
     * @return a list of AgendaEntryDto objects managed by the specified GSM.
     */
    public List<AgendaEntry> getAgendaEntryListByGSM(UserSession GSM) {
        List<AgendaEntry> z = new ArrayList<>();
        for (AgendaEntry s : getAgendaEntryList()) {
            if (s.getGreenSpace().getManager().equals(GSM.getUserEmail())) {
                z.add(s);
            }
        }
        return z;
    }

    public List<AgendaEntry> getAgendaEntryListByEmail(String email) {
        List<AgendaEntry> z = new ArrayList<>();
        for (AgendaEntry s : getAgendaEntryList()) {
            if (s.getGreenSpace().getManager().equals(email)) {
                z.add(s);
            }
        }
        return z;
    }

    /**
     * Updates the vehicles assigned to a specific AgendaEntry.
     *
     * @param entryIndex the index of the AgendaEntry to update.
     * @param f          the list of VehicleDto objects to assign.
     */
    public void updateVehiclesAgendaEntry(int entryIndex, List<VehicleDto> f) {
        List<Vehicle> u = VehicleMapper.toDomainList(f);
        agendaEntryList.get(entryIndex).setAssignedVehicles(u);
    }

    /**
     * Updates the date and progress status of an AgendaEntry.
     *
     * @param agendaEntry       the AgendaEntry to update.
     * @param newDate           the new date to set.
     * @param newProgressStatus the new progress status to set.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateAgendaEntry(AgendaEntry agendaEntry, LocalDate newDate, ProgressStatus newProgressStatus) {
        if (validateNewDate(agendaEntry, newDate)) {
            updateEntryStatus(agendaEntry, newProgressStatus);
            AgendaEntry agendaEntry1 = new AgendaEntry(agendaEntry, newDate, newProgressStatus);
            addAgendaEntry(agendaEntry1);

            return true;
        }
        return false;
    }

    /**
     * Validates the new date for an AgendaEntry.
     *
     * @param agendaEntry the AgendaEntry to validate.
     * @param newDate     the new date to validate.
     * @return true if the new date is valid, false otherwise.
     * @throws IllegalArgumentException if the new date is null or before the current date.
     */
    private Boolean validateNewDate(AgendaEntry agendaEntry, LocalDate newDate) {
        if (newDate == null) {
            throw new IllegalArgumentException("New date cannot be null.");
        } else if (newDate.isBefore(agendaEntry.getStartingDate())) {
            throw new IllegalArgumentException("New date must be after the current estimated date.");
        }
        return true;
    }

    /**
     * Updates the progress status of an AgendaEntry.
     *
     * @param agendaEntry       the AgendaEntry to update.
     * @param newProgressStatus the new progress status to set.
     */
    private void updateEntryStatus(AgendaEntry agendaEntry, ProgressStatus newProgressStatus) {
        agendaEntry.setProgressStatus(newProgressStatus);
    }

    /**
     * Sets a new date for an AgendaEntry.
     *
     * @param agendaEntry the AgendaEntry to update.
     * @param newDate     the new date to set.
     */
    private void setNewDate(AgendaEntry agendaEntry, LocalDate newDate) {
        agendaEntry.setStartingDate(newDate);
    }

    /**
     * Retrieves a list of all AgendaEntryDto objects.
     *
     * @return a list of all AgendaEntryDto objects.
     */
    public List<AgendaEntry> getAgendaEntryList() {
        return (agendaEntryList);
    }

    /**
     * Gets dates list.
     *
     * @param beginningDate the beginning date
     * @param endDate       the end date
     * @param status        the status
     * @param team          the team
     * @return the dates list
     */
    public List<AgendaEntry> getDatesList(LocalDate beginningDate , LocalDate endDate, ProgressStatus status, Team team) {
        List<AgendaEntry> fullList = getAgendaEntryList();

        List<AgendaEntry> teamEntries = new ArrayList<>();
        for (AgendaEntry entry : fullList) {
            if (entry.getTeam().equals(team)) {
                teamEntries.add(entry);
            }
        }

        for (AgendaEntry entry : teamEntries) {
            if (entry.getStartingDate().isBefore(beginningDate)|| entry.getStartingDate().isAfter(endDate)) {
                teamEntries.remove(entry);
            }
        }
        return teamEntries;

    }
    /**
     * Retrieves a list of all ToDoEntryDto objects.
     *
     * @return a list of all ToDoEntryDto objects.
     */
    public List<ToDoEntry> getToDoEntryList() {
        return toDoEntryList;
    }

    /**
     * Records the completion of an AgendaEntry.
     *
     * @param entry  the AgendaEntry to update.
     * @param status the new progress status to set.
     * @return true if the entry is updated successfully, false otherwise.
     */
    public boolean recordAgendaEntryCompletion(AgendaEntry entry, ProgressStatus status) {
        if (entry != null && status == ProgressStatus.COMPLETED) {
            entry.setProgressStatus(status);
            entry.setAvailable(entry);
            return true;
        }
        return false;
    }

    /**
     * Remove to do entry.
     *
     * @param toDoEntry the to do entry
     */
    public void removeToDoEntry(ToDoEntry toDoEntry) {
        toDoEntryList.remove(toDoEntry);
    }

    /**
     * Find to do entry by title to do entry.
     *
     * @param title the title
     * @return the to do entry
     */
    public ToDoEntry findToDoEntryByTitle(String title) {
        return toDoEntryList.stream()
                .filter(entry -> entry.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    /**
     * Remove.
     *
     * @param title      the title
     * @param greenSpace the green space
     */
    public void remove(String title, String greenSpace) {
        toDoEntryList.removeIf(ToDoEntry -> ToDoEntry.getTitle().equals(title) && ToDoEntry.getGreenSpace().getName().equals(greenSpace));
    }


}
