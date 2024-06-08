package pt.ipp.isep.dei.project.domain;

public class Route {
    private final Point startPoint;
    private final Point endPoint;
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
     *
     * @return the starting point
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the ending point of this route
     *
     * @return the ending point
     */
    public Point getEndPoint() {
        return endPoint;
    }


    /**
     * Constructs a Route object with the given starting point, end point and cost.
     *
     * @param startPoint the starting point of the route
     * @param endPoint   the ending point of the route
     * @param cost       the cost of crossing the route
     */
    public Route(Point startPoint, Point endPoint, double cost) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost = cost;
    }

    /**
     * Checks if this route is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return route.cost == cost && route.getEndPoint().getID().equals(endPoint.getID()) && route.getStartPoint().getID().equals(startPoint.getID());
    }

    @Override
    public int hashCode() {
        int result = startPoint.hashCode();
        result = 31 * result + endPoint.hashCode();
        return result;
    }

}

