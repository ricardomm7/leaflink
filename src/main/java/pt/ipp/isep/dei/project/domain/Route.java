package pt.ipp.isep.dei.project.domain;

public class Route {
    private final WaterPoint startPoint;
    private final WaterPoint endPoint;
    private final double cost;

    /**
     * Returns the cost of crossing this route
     *
     * @return the cost of the route
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the starting point of this route
     * @return the starting point
     */
    public WaterPoint getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the ending point of this route
     * @return the ending point
     */
    public WaterPoint getEndPoint() {
        return endPoint;
    }


    /**
     * Constructs a Route object with the given starting point, end point and cost.
     *
     * @param startPoint the starting point of the route
     * @param endPoint   the ending point of the route
     * @param cost       the cost of crossing the route
     */
    public Route(WaterPoint startPoint, WaterPoint endPoint, double cost) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost = cost;
    }
}

