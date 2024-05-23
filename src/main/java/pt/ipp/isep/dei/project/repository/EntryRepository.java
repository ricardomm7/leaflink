package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.Status;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Entry repository.
 */
public class EntryRepository {

    private final List<Entry> entryList;

    /**
     * Instantiates a new Entry repository.
     */
    public EntryRepository() {
        entryList = new ArrayList<>();
    }

    public void create(Entry entry) {
        if (checkForDuplicates(entry)) {
            addEntry(entry);
        } else {
            throw new IllegalArgumentException("There is already an entry with the same description.");
        }
    }

    private boolean checkForDuplicates(Entry entry) {
        return entryList.stream()
                .noneMatch(e -> e.getDescription().equalsIgnoreCase(entry.getDescription()));
    }

    private void addEntry(Entry entry) {
        entryList.add(entry);
    }

    /**
     * Gets entry list by GSM.
     *
     * @param gsmEmail the gsm email
     * @return the entry list by gsm
     */
    public List<Entry> getEntryListByGSM(String gsmEmail) {
        // Lógica para obter a lista de entradas para o GSM específico
        return entryList;
    }

    /**
     * Update entry.
     *
     * @param entry     the entry
     * @param newDate   the new date
     * @param newStatus the new status
     * @return true if the update was successful, false otherwise
     */
    public boolean updateEntry(Entry entry, LocalDate newDate, Status newStatus) {
        if (validateNewDate(entry, newDate)) {
            updateEntryStatus(entry, newStatus);
            setNewDate(entry, newDate);
            return true;
        }
        return false;
    }

    private Boolean validateNewDate(Entry entry, LocalDate newDate) {
        if (newDate == null) {
            throw new IllegalArgumentException("New date cannot be null.");
        } else if (newDate.isBefore(entry.getEstimatedDate())) {
            throw new IllegalArgumentException("New date must be after the current estimated date.");
        }
        return true;
    }

    private void updateEntryStatus(Entry entry, Status newStatus) {
        entry.setStatus(newStatus);
    }

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



