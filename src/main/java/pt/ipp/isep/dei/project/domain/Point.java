package pt.ipp.isep.dei.project.domain;

public class Point {

    // The unique identifier of a point in the graph, that is a water point in the context of the graph
    private final String ID;

    /**
     * Returns the unique identifier of the point.
     *
     * @return the identifier of the point
     */
    public String getID() {
        return ID;
    }

    /**
     * Constructs a Point object with the given identifier.
     *
     * @param nome the unique identifier of the point
     */
    public Point(String nome) {

        this.ID = nome;
    }
}