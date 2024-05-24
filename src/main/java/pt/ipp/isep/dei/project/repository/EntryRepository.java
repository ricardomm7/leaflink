package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.mappers.EntryMapper;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Entry repository.
 */
public class EntryRepository implements Serializable {

    private final List<Entry> entryList;

    /**
     * Instantiates a new Entry repository.
     */
    public EntryRepository() {
        entryList = new ArrayList<>();
    }

    public void create(EntryDto dto) {
        Entry entry = EntryMapper.toDomain(dto);
        if (checkForDuplicates(entry)) {
            addEntry(entry);
        } else {
            throw new IllegalArgumentException("There is already an entry with the same description.");
        }
    }

    /**
     * Check for duplicates boolean.
     *
     * @param entry the entry
     * @return the boolean
     */
    private boolean checkForDuplicates(Entry entry) {
        return entryList.stream()
                .noneMatch(e -> e.getDescription().equalsIgnoreCase(entry.getDescription()));
    }

    /**
     * Add entry.
     *
     * @param entry the entry
     */
    private void addEntry(Entry entry) {
        entryList.add(entry);
    }

    /**
     * Gets entry list by GSM.
     *
     * @param gsm the gsm
     * @return the entry list by gsm
     */
    public List<EntryDto> getEntryListByGSM(UserSession gsm) {
        List<EntryDto> z = new ArrayList<>();
        for (Entry s : entryList) {
            if (s.getGreenSpace().getManager().getUserEmail().equals(gsm.getUserEmail())) {
                z.add(EntryMapper.toDto(s));
            }
        }
        return z;
    }


    /**
     * Update entry boolean.
     *
     * @param entry             the entry
     * @param newDate           the new date
     * @param newProgressStatus the new status
     * @return true if the update was successful, false otherwise
     */
    public boolean updateEntry(Entry entry, LocalDate newDate, ProgressStatus newProgressStatus) {
        if (validateNewDate(entry, newDate)) {
            updateEntryStatus(entry, newProgressStatus);
            setNewDate(entry, newDate);

            return true;
        }
        return false;
    }

    /**
     * Validate new date boolean.
     *
     * @param entry   the entry
     * @param newDate the new date
     * @return the boolean
     */
    private Boolean validateNewDate(Entry entry, LocalDate newDate) {
        if (newDate == null) {
            throw new IllegalArgumentException("New date cannot be null.");
        } else if (newDate.isBefore(entry.getEstimatedDate())) {
            throw new IllegalArgumentException("New date must be after the current estimated date.");
        }
        return true;
    }

    /**
     * Update entry status.
     *
     * @param entry             the entry
     * @param newProgressStatus the new status
     */
    private void updateEntryStatus(Entry entry, ProgressStatus newProgressStatus) {
        entry.setStatus(newProgressStatus);
    }

    /**
     * Sets new date.
     *
     * @param entry   the entry
     * @param newDate the new date
     */
    private void setNewDate(Entry entry, LocalDate newDate) {
        entry.setEstimatedDate(newDate);
    }

    /**
     * Gets entry list.
     *
     * @return the entry list
     */
    public List<EntryDto> getEntryList() {
        List<EntryDto> r = new ArrayList<>();
        for (Entry e : entryList) {
            r.add(new EntryDto(GreenSpaceMapper.toDto(e.getGreenSpace()), e.getDescription(), e.getDegreeOfUrgency(), e.getDuration()));
        }
        return r;
    }
}





