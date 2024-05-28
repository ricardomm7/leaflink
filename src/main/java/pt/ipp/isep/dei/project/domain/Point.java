package pt.ipp.isep.dei.project.domain;

/**
 * The Point class represents a point in a graph, particularly a water point.
 * It holds a unique identifier for the point.
 */
public class Point {

    /**
     * The unique identifier of the point.
     */
    private final String ID;

    /**
     * Constructs a point with the given identifier.
     *
     * @param ID the unique identifier of the point
     */
    public Point(String ID) {
        this.ID = ID;
    }

    /**
     * Gets the unique identifier of the point.
     *
     * @return the unique identifier of the point
     */
    public String getID() {
        return ID;
    }
}
