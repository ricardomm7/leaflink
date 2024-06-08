package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EvacuationController {

    public static List<Integer> index = new ArrayList<>();

    public void run(String matrix, String names) throws IOException {
        ExternalCSV e1 = new ExternalCSV(matrix, names);

        e1.readRoutesFromMatrixAndNames();
        List<Route> routes = e1.getAllRoutes();

        List<List<Route>> allShortestPaths = new ArrayList<>();

        // Find all AP points
        List<Point> allAPPoints = new ArrayList<>();
        for (Integer apIndex : index) {
            allAPPoints.add(e1.getAllPoints().get(apIndex));
        }

        int j = 0;
        for (Point pointIn : e1.getAllPoints()) {
            List<Route> shortestPath = DijkstraAlgorithm.findShortestPathToAnyEndPoint(pointIn, allAPPoints, routes);
            allShortestPaths.add(shortestPath);
            Graphviz.executeDijkra(routes, shortestPath, "outMstRouteEvacuation_Point-" + j);
            j++;
        }

        ExternalCSV.exportRoutesDijsktraToCSV(allShortestPaths);
    }
}


