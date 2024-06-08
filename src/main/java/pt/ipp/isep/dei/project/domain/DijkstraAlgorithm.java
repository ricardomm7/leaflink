package pt.ipp.isep.dei.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The DijkstraAlgorithm class provides a method to find the shortest path between two points
 * in a graph using Dijkstra's algorithm.
 */
public abstract class DijkstraAlgorithm {
    private static List<Route> allRoutes;

    /**
     * Finds the shortest path between the given start point and any of the end points using Dijkstra's algorithm.
     *
     * @param startPoint the starting point of the path
     * @param endPoints  a list of possible end points
     * @param allRoutes  a list of all routes (edges) in the graph
     * @return a list of routes that represent the shortest path from startPoint to the closest endPoint
     */
    public static List<Route> findShortestPathToAnyEndPoint(Point startPoint, List<Point> endPoints, List<Route> allRoutes) {
        DijkstraAlgorithm.allRoutes = allRoutes;

        List<Point> points = new ArrayList<>();
        for (Route route : allRoutes) {
            addUniquePoint(points, route.getStartPoint());
            addUniquePoint(points, route.getEndPoint());
        }

        double[] distances = new double[points.size()];
        String[] predecessors = new String[points.size()];
        boolean[] visited = new boolean[points.size()];

        // Initializing arrays with default values
        for (int i = 0; i < points.size(); i++) {
            distances[i] = Double.MAX_VALUE;
            predecessors[i] = null;
            visited[i] = false;
        }

        int startIndex = getPointIndex(points, startPoint);
        distances[startIndex] = 0.0;

        List<PointDistance> pointDistances = new ArrayList<>();
        pointDistances.add(new PointDistance(startPoint, 0.0));

        while (!pointDistances.isEmpty()) {
            PointDistance pd = getMinimumDistancePoint(pointDistances);
            Point currentPoint = pd.point;
            int currentIndex = getPointIndex(points, currentPoint);

            if (visited[currentIndex]) continue;
            visited[currentIndex] = true;

            for (Route route : allRoutes) {
                if (route.getStartPoint().equals(currentPoint)) {
                    Point neighbor = route.getEndPoint();
                    int neighborIndex = getPointIndex(points, neighbor);
                    double newDistance = distances[currentIndex] + route.getCost();

                    if (newDistance < distances[neighborIndex]) {
                        distances[neighborIndex] = newDistance;
                        predecessors[neighborIndex] = currentPoint.getID();
                        pointDistances.add(new PointDistance(neighbor, newDistance));
                    }
                }
            }
        }

        Point closestEndPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point endPoint : endPoints) {
            int endIndex = getPointIndex(points, endPoint);
            if (distances[endIndex] < minDistance) {
                minDistance = distances[endIndex];
                closestEndPoint = endPoint;
            }
        }

        return buildPath(startPoint, closestEndPoint, points, predecessors);
    }

    /**
     * Adds a point to the list if it is not already present.
     *
     * @param points the list of points
     * @param point  the point to be added
     */
    private static void addUniquePoint(List<Point> points, Point point) {
        for (Point p : points) {
            if (p.getID().equals(point.getID())) {
                return;
            }
        }
        points.add(point);
    }

    /**
     * Returns the index of the given point in the list.
     *
     * @param points the list of points
     * @param point  the point whose index is to be found
     * @return the index of the point, or -1 if the point is not in the list
     */
    private static int getPointIndex(List<Point> points, Point point) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getID().equals(point.getID())) {
                return i;
            }
        }
        return -1; // Should not happen if points list is properly built
    }

    /**
     * Returns the point with the minimum distance from the list and removes it from the list.
     *
     * @param pointDistances the list of points with their distances
     * @return the point with the minimum distance
     */
    private static PointDistance getMinimumDistancePoint(List<PointDistance> pointDistances) {
        PointDistance minPointDistance = null;
        for (PointDistance pd : pointDistances) {
            if (minPointDistance == null || pd.distance < minPointDistance.distance) {
                minPointDistance = pd;
            }
        }
        pointDistances.remove(minPointDistance);
        return minPointDistance;
    }

    /**
     * Builds the shortest path from the start point to the end point using the predecessors array.
     *
     * @param startPoint   the starting point of the path
     * @param endPoint     the ending point of the path
     * @param points       the list of points in the graph
     * @param predecessors the array of predecessors for each point
     * @return a list of routes representing the shortest path, or an empty list if no path is found
     */
    private static List<Route> buildPath(Point startPoint, Point endPoint, List<Point> points, String[] predecessors) {
        List<Route> path = new ArrayList<>();
        Point currentPoint = endPoint;

        while (currentPoint != null && !currentPoint.getID().equals(startPoint.getID())) {
            int currentIndex = getPointIndex(points, currentPoint);
            String predecessorID = predecessors[currentIndex];
            if (predecessorID == null) {
                return new ArrayList<>(); // No path found
            }

            Point predecessorPoint = null;
            for (Point point : points) {
                if (point.getID().equals(predecessorID)) {
                    predecessorPoint = point;
                    break;
                }
            }

            Route route = getRoute(predecessorPoint, currentPoint, allRoutes);
            if (route != null) {
                path.add(0, route);
            }

            currentPoint = predecessorPoint;
        }

        return path;
    }

    /**
     * Returns the route connecting the given start and end points.
     *
     * @param startPoint the starting point of the route
     * @param endPoint   the ending point of the route
     * @param allRoutes  the list of all routes in the graph
     * @return the route connecting the start and end points, or null if no such route exists
     */
    private static Route getRoute(Point startPoint, Point endPoint, List<Route> allRoutes) {
        for (Route route : allRoutes) {
            if (route.getStartPoint().equals(startPoint) && route.getEndPoint().equals(endPoint)) {
                return route;
            }
        }
        return null;
    }

    /**
     * The PointDistance class represents a point and its distance from the start point.
     */
    private static class PointDistance {
        Point point;
        double distance;

        PointDistance(Point point, double distance) {
            this.point = point;
            this.distance = distance;
        }
    }
}
