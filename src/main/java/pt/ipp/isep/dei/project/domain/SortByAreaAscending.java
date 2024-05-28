package pt.ipp.isep.dei.project.domain;

import java.util.Comparator;
import java.util.List;

/**
 * The SortByAreaAscending class provides a sorting algorithm to sort a list of GreenSpace objects by area in ascending order.
 */
public class SortByAreaAscending implements SortAlgorithms {

    /**
     * Sorts a list of GreenSpace objects by area in ascending order.
     *
     * @param g The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    @Override
    public List<GreenSpace> sort(List<GreenSpace> g) {
        g.sort(Comparator.comparingDouble(GreenSpace::getArea));
        return g;
    }
}
