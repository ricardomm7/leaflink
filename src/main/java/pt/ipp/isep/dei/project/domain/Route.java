package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The Route class represents a route between two points in a graph.
 */
public class Route implements Serializable {

    // The starting point of the route
    private final Point startPoint;

    // The ending point of the route
    private final Point endPoint;

    // The cost of traversing the route
    private final double cost;

    /**
     * Constructs a route with the given starting point, ending point, and cost.
     *
     * @param startPoint The starting point of the route.
     * @param endPoint   The ending point of the route.
     * @param cost       The cost of traversing the route.
     */
    public Route(Point startPoint, Point endPoint, double cost) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost = cost;
    }

    /**
     * Gets the cost of traversing the route.
     *
     * @return The cost of traversing the route.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the starting point of the route.
     *
     * @return The starting point of the route.
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Gets the ending point of the route.
     *
     * @return The ending point of the route.
     */
    public Point getEndPoint() {
        return endPoint;
    }
}
