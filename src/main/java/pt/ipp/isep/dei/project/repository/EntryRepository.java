package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * The EntryRepository class manages the persistence of ToDoEntry and AgendaEntry objects.
 * It provides methods to create, update, and retrieve these entries.
 */
public class EntryRepository implements Serializable {
    private final List<ToDoEntry> toDoEntryList;
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
    public void createNewToDoEntry(ToDoEntryDto dto) {
        ToDoEntry entry = ToDoEntryMapper.toDomain(dto);
        if (checkForDuplicates(entry)) {
            addToDoEntry(entry);
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
     * @param email the email of the UserSession of the Green Space Manager.
     * @return a list of AgendaEntryDto objects managed by the specified GSM.
     */
    public List<AgendaEntry> getAgendaEntryListByGSM(String email) {
        List<AgendaEntry> agendaEntries = new ArrayList<>();
        for (AgendaEntry agendaEntry : agendaEntryList) {
            if (agendaEntry.getGreenSpace().getManager().equals(email)) {
                agendaEntries.add(agendaEntry);
            }
        }
        return agendaEntries;
    }

    /**
     * Updates the vehicles assigned to a specific AgendaEntry.
     *
     * @param agendaEntry the index of the AgendaEntry to update.
     * @param f           the list of VehicleDto objects to assign.
     */
    public void updateVehiclesAgendaEntry(AgendaEntry agendaEntry, List<VehicleDto> f) {
        List<Vehicle> u = VehicleMapper.toDomainList(f);
        for (AgendaEntry agendaEntry1 : agendaEntryList) {
            if (agendaEntry.getTitle().equalsIgnoreCase(agendaEntry1.getTitle()) && agendaEntry.getDescription().
                    equalsIgnoreCase(agendaEntry1.getDescription()) && agendaEntry.getStartingDate().isEqual(agendaEntry1.getStartingDate()))
                agendaEntry1.setAssignedVehicles(u);
        }
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
            AgendaEntry agendaEntry1 = new AgendaEntry(agendaEntry, newDate, ProgressStatus.PLANNED);
            addAgendaEntry(agendaEntry1);
            return true;
        } else {
            ShowError.showAlert("Postpone Entry", "The new date cannot be before the starting date.", "Invalid Date");
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
        for (AgendaEntry e : agendaEntryList) {
            if (e.getTitle().equalsIgnoreCase(agendaEntry.getTitle()) && e.getStartingDate().isEqual(agendaEntry.getStartingDate()) && e.getDescription().equalsIgnoreCase(agendaEntry.getDescription())) {
                agendaEntry.setProgressStatus(newProgressStatus);
            }
        }
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
     * @param team          the team
     * @return the dates list
     */
    public List<AgendaEntry> getDatesList(LocalDate beginningDate, LocalDate endDate, Team team) {
        List<AgendaEntry> teamEntries = new ArrayList<>();
        for (AgendaEntry entry : agendaEntryList) {
            try {
                if (entry.getAssignedTeam().getCollaborators().get(0).getTaxpayerNumber() == team.getCollaborators().get(0).getTaxpayerNumber()) {
                    teamEntries.add(entry);
                }
            } catch (Exception ignored) {
            }
        }

        for (AgendaEntry entry : teamEntries) {
            if (entry.getStartingDate().isBefore(beginningDate) || entry.getStartingDate().isAfter(endDate)) {
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
        for (AgendaEntry entry1 : agendaEntryList) {
            if (entry.getTitle().equalsIgnoreCase(entry1.getTitle()) && entry.getDescription().equalsIgnoreCase(entry1.getDescription()) &&
                    entry.getStartingDate().isEqual(entry1.getStartingDate())) {
                if (entry1.getProgressStatus() != ProgressStatus.COMPLETED) {
                    entry1.setProgressStatus(status);
                    return true;
                } else {
                    ShowError.showAlert("Error", "Entry already completed or cancelled ", null);
                }
            }
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

    public void updateTeamAgendaEntry(AgendaEntry agendaEntry, Team team) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.getTitle().equalsIgnoreCase(agendaEntry.getTitle()) && entry.getDescription().equalsIgnoreCase(agendaEntry.getDescription()) && entry.getStartingDate().equals(agendaEntry.getStartingDate())) {
                entry.setAssignedTeam(team);
            }
        }
    }

    public boolean cancelAgendaEntry(AgendaEntry agendaEntry) {
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.getTitle().equalsIgnoreCase(agendaEntry.getTitle()) && entry.getDescription().equalsIgnoreCase(agendaEntry.getDescription()) && entry.getStartingDate().equals(agendaEntry.getStartingDate()) &&
                    entry.getProgressStatus().equals(agendaEntry.getProgressStatus())) {
                entry.setProgressStatus(ProgressStatus.CANCELLED);
                return true;
            }
        }
        return false;
    }

    public List<AgendaEntry> getAgendaEntriesAssignedToCollaborator(UserSession collaborator) {
        List<AgendaEntry> agendaEntries = new ArrayList<>();
        for (AgendaEntry entry : agendaEntryList) {
            if (entry.getAssignedTeam() != null) {
                for (Collaborator c : entry.getAssignedTeam().getCollaborators()) {
                    if (c.getEmail().equalsIgnoreCase(collaborator.getUserEmail())) {
                        agendaEntries.add(entry);
                    }
                }
            }
        }
        return agendaEntries;
    }
}
