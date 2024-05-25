package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type ToDoEntry repository.
 */
public class EntryRepository implements Serializable {

    private final List<ToDoEntry> toDoEntryList;
    private final List<AgendaEntryDto> agendaEntryDtoList;

    /**
     * Instantiates a new ToDoEntry repository.
     */
    public EntryRepository() {
        agendaEntryDtoList = new ArrayList<>();
        toDoEntryList = new ArrayList<>();
    }

    public void create(ToDoEntryDto dto) {
        ToDoEntry toDoEntry = ToDoEntryMapper.toDomain(dto);
        if (checkForDuplicates(toDoEntry)) {
            addEntry(toDoEntry);
        } else {
            throw new IllegalArgumentException("There is already an toDoEntry with the same description.");
        }
    }

    /**
     * Check for duplicates boolean.
     *
     * @param toDoEntry the toDoEntry
     * @return the boolean
     */
    private boolean checkForDuplicates(ToDoEntry toDoEntry) {
        return toDoEntryList.stream()
                .noneMatch(e -> e.getDescription().equalsIgnoreCase(toDoEntry.getDescription()));
    }

    /**
     * Add toDoEntry.
     *
     * @param toDoEntry the toDoEntry
     */
    private void addEntry(ToDoEntry toDoEntry) {
        toDoEntryList.add(toDoEntry);
    }

    /**
     * Gets entry list by GSM.
     *
     * @param GSM the gsm
     * @return the entry list by gsm
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
     * Validate new date boolean.
     *
     * @param agendaEntryDto the toDoEntry
     * @param newDate        the new date
     * @return the boolean
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
     * Update toDoEntry status.
     *
     * @param agendaEntry       the toDoEntry
     * @param newProgressStatus the new status
     */
    private void updateEntryStatus(pt.ipp.isep.dei.project.domain.AgendaEntry agendaEntry, ProgressStatus newProgressStatus) {
        agendaEntry.setProgressStatus(newProgressStatus);
    }

    /**
     * Sets new date.
     *
     * @param agendaEntry the toDoEntry
     * @param newDate     the new date
     */
    private void setNewDate(pt.ipp.isep.dei.project.domain.AgendaEntry agendaEntry, LocalDate newDate) {
        agendaEntry.setStartingDate(newDate);
    }

    /**
     * Gets entry list.
     *
     * @return the entry list
     */
    public List<AgendaEntryDto> getAgendaEntryList() {
        List<AgendaEntryDto> r = new ArrayList<>();
        for (AgendaEntryDto e : agendaEntryDtoList) {
            r.add(new AgendaEntryDto(e.getTitle(), e.getDescription(), e.getDuration(), e.getUrgencyStatus(), e.getGreenSpace(), e.getStartingDate(), e.getProgressStatus()));
        }
        return r;
    }

    public List<ToDoEntryDto> getToDoEntryList() {
        List<ToDoEntryDto> r = new ArrayList<>();
        for (ToDoEntry e : toDoEntryList) {
            r.add(new ToDoEntryDto(e.getTitle(), e.getDescription(), e.getDuration(), e.getUrgencyStatus(), GreenSpaceMapper.toDto(e.getGreenSpace())));
        }
        return r;
    }

        public boolean recordToDoEntryCompletion(AgendaEntry entry, ProgressStatus status) {
        if (entry != null && status == ProgressStatus.COMPLETED) {
            entry.setProgressStatus(status);
            return true;
        }
        return false;
    }
}





