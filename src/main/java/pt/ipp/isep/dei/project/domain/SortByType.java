package pt.ipp.isep.dei.project.domain;

import java.util.Comparator;
import java.util.List;

/**
 * The SortByType class provides a sorting algorithm to sort a list of GreenSpace objects by type.
 */
public class SortByType implements Sortable {

    /**
     * Sorts a list of GreenSpace objects by type.
     *
     * @param g The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    @Override
    public List<GreenSpace> sort(List<GreenSpace> g) {
        g.sort(Comparator.comparing(GreenSpace::getType));
        return g;
    }
}
