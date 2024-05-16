package pt.ipp.isep.dei.project.domain;

public class WaterPoint {

    // The unique identifier of a point in the graph, that is a water point in the context of the graph
    private final String ID;

    /**
     * Returns the unique identifier of the water point.
     * @return the identifier of the water point
     */
    public String getID() {

        return ID;
    }

    /**
     * Constructs a WaterPoint object with the given identifier.
     * @param nome the unique identifier of the water point
     */
    public WaterPoint(String nome) {

        this.ID = nome;
    }
}