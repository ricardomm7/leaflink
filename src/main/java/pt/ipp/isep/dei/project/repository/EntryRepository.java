
package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The EntryRepository class is responsible for managing the collections of ToDoEntry and AgendaEntry objects.
 * It provides methods for creating, retrieving, updating, and manipulating these entries.
 */
public class EntryRepository implements Serializable {

    private final List<ToDoEntry> toDoEntryList;
    private final List<AgendaEntry> agendaEntryList;

    /**
     * Constructs a new EntryRepository object and initializes the lists for ToDoEntry and AgendaEntry objects.
     */
    public EntryRepository() {
        agendaEntryList = new ArrayList<>();
        toDoEntryList = new ArrayList<>();
    }

    /**
     * Creates a new ToDoEntry based on the provided ToDoEntryDto.
     *
     * @param dto the ToDoEntryDto containing the ToDoEntry data
     */
    public void create(ToDoEntryDto dto) {
        ToDoEntry toDoEntry = ToDoEntryMapper.toDomain(dto);
        if (checkForDuplicates(toDoEntry)) {
            addEntry(toDoEntry);
        } else {
            throw new IllegalArgumentException("There is already an toDoEntry with the same description.");
        }
    }

    /**
     * Checks if a ToDoEntry with the same description already exists in the repository.
     *
     * @param toDoEntry the ToDoEntry object to check for duplicates
     * @return true if no duplicate is found, false otherwise
     */
    private boolean checkForDuplicates(ToDoEntry toDoEntry) {
        return toDoEntryList.stream()
                .noneMatch(e -> e.getDescription().equalsIgnoreCase(toDoEntry.getDescription()));
    }

    /**
     * Adds a ToDoEntry to the repository.
     *
     * @param toDoEntry the ToDoEntry object to be added
     */
    private void addEntry(ToDoEntry toDoEntry) {
        toDoEntryList.add(toDoEntry);
    }

    /**
     * Gets the list of AgendaEntryDto objects associated with the given Green Space Manager (GSM).
     *
     * @param GSM the UserSession object representing the Green Space Manager
     * @return the list of AgendaEntryDto objects associated with the GSM
     */
    public List<AgendaEntryDto> getAgendaEntryListByGSM(UserSession GSM) {
        List<AgendaEntryDto> z = new ArrayList<>();
        for (AgendaEntryDto s : getAgendaEntryList()) {
            if (s.getGreenSpace().getManager().getUserEmail().equals(GSM.getUserEmail())) {
                z.add(s);
            }
        }
        return z;
    }

    /**
     * Updates the assigned vehicles for an AgendaEntry at the specified index.
     *
     * @param entryIndex the index of the AgendaEntry to be updated
     * @param f          the list of VehicleDto objects representing the new assigned vehicles
     */
    public void updateVehiclesAgendaEntry(int entryIndex, List<VehicleDto> f) {
        List<Vehicle> u = VehicleMapper.toDomainList(f);
        agendaEntryList.get(entryIndex).setAssignedVehicles(u);
    }

    /**
     * Updates an AgendaEntry with a new date and progress status.
     *
     * @param agendaEntrydto    the AgendaEntryDto object representing the AgendaEntry to be updated
     * @param newDate           the new date for the AgendaEntry
     * @param newProgressStatus the new progress status for the AgendaEntry
     * @return true if the update is successful, false otherwise
     */
    public boolean updateAgendaEntry(AgendaEntryDto agendaEntrydto, LocalDate newDate, ProgressStatus newProgressStatus) {
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(agendaEntrydto);
        if (validateNewDate(agendaEntry, newDate)) {
            updateEntryStatus(agendaEntry, newProgressStatus);
            setNewDate(agendaEntry, newDate);

            return true;
        }
        return false;
    }

    /**
     * Validates the new date for an AgendaEntry.
     *
     * @param agendaEntryDto the AgendaEntry object
     * @param newDate        the new date to be validated
     * @return true if the new date is valid, false otherwise
     */
    private Boolean validateNewDate(AgendaEntry agendaEntryDto, LocalDate newDate) {
        if (newDate == null) {
            throw new IllegalArgumentException("New date cannot be null.");
        } else if (newDate.isBefore(agendaEntryDto.getStartingDate())) {
            throw new IllegalArgumentException("New date must be after the current estimated date.");
        }
        return true;
    }

    /**
     * Updates the progress status of an AgendaEntry.
     *
     * @param agendaEntry      the AgendaEntry object
     * @param newProgressStatus the new progress status
     */
    private void updateEntryStatus(AgendaEntry agendaEntry, ProgressStatus newProgressStatus) {
        agendaEntry.setProgressStatus(newProgressStatus);
    }

    /**
     * Sets a new date for an AgendaEntry.
     *
     * @param agendaEntry the AgendaEntry object
     * @param newDate     the new date
     */
    private void setNewDate(AgendaEntry agendaEntry, LocalDate newDate) {
        agendaEntry.setStartingDate(newDate);
    }

    /**
     * Gets the list of AgendaEntryDto objects.
     *
     * @return the list of AgendaEntryDto objects
     */
    public List<AgendaEntryDto> getAgendaEntryList() {
        return AgendaEntryMapper.toDtoList(agendaEntryList);
    }

    /**
     * Gets the list of ToDoEntryDto objects.
     *
     * @return the list of ToDoEntryDto objects
     */
    public List<ToDoEntryDto> getToDoEntryList() {
        List<ToDoEntryDto> r = new ArrayList<>();
        for (ToDoEntry e : toDoEntryList) {
            r.add(new ToDoEntryDto(e.getTitle(), e.getDescription(), e.getDuration(), e.getUrgencyStatus(), GreenSpaceMapper.toDto(e.getGreenSpace())));
        }
        return r;
    }

    /**
     * Records the completion of an AgendaEntry by setting its progress status to COMPLETE.
     *
     * @param entry  the AgendaEntry object
     * @param status the ProgressStatus to be set (should be COMPLETED)
     * @return true if the operation is successful, false otherwise
     */
    public boolean recordAgendaEntryCompletion(AgendaEntry entry, ProgressStatus status) {
        if (entry != null && status == ProgressStatus.COMPLETED) {
            entry.setProgressStatus(status);
            return true;
        }
        return false;
    }
}
