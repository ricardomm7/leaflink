package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.*;

import java.util.ArrayList;
import java.util.List;

public class EntryRepository {

    private final List<Entry> entryList;

    public EntryRepository() {
        entryList = new ArrayList<>();
    }

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

    public List<Entry> getEntryList() {
        return entryList;
    }
}
