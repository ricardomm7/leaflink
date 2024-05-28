package pt.ipp.isep.dei.project.domain;

import java.util.List;

/**
 * The SortAlgorithms interface defines a contract for sorting algorithms.
 */
public interface SortAlgorithms {

    /**
     * Sorts a list of GreenSpace objects.
     *
     * @param g The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    List<GreenSpace> sort(List<GreenSpace> g);
}
