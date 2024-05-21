package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.*;

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

    /**
     * Create.
     *
     * @param greenSpace      the green space
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public void create(GreenSpace greenSpace, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
    Entry g = new Entry(greenSpace,description,degreeOfUrgency,duration);
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
    public List<Entry> getEntryList() {
        return entryList;
    }
}
