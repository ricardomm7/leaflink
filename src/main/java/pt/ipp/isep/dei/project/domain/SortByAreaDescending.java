package pt.ipp.isep.dei.project.domain;

import java.util.Comparator;
import java.util.List;

/**
 * The SortByAreaDescending class provides a sorting algorithm to sort a list of GreenSpace objects by area in descending order.
 */
public class SortByAreaDescending implements Sortable {

    /**
     * Sorts a list of GreenSpace objects by area in descending order.
     *
     * @param g The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    @Override
    public List<GreenSpace> sort(List<GreenSpace> g) {
        g.sort(Comparator.comparingDouble(GreenSpace::getArea).reversed());
        return g;
    }
}
