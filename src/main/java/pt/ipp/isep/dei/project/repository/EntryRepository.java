package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;

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

    public void create(Entry g) {
        if (checkForDuplicates(g)) {
            addEntry(g);
        } else {
            throw new IllegalArgumentException("There is already a green space with that name.");
        }
    }

    private boolean checkForDuplicates(Entry g) {
        for (Entry x : entryList) {
            if (x.getDescription().equalsIgnoreCase(g.getDescription())) {
                return false;
            }
        }
        return true;
    }

    private void addEntry(Entry g) {
        entryList.add(g);
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
