package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.ExternalCSV;
import pt.ipp.isep.dei.project.domain.Graphviz;
import pt.ipp.isep.dei.project.domain.KruskalAlgorithm;
import pt.ipp.isep.dei.project.domain.Route;

import java.util.List;

/**
 * The type Water irrigation controller.
 */
public class WaterIrrigationController {
    /**
     * Execute.
     *
     * @param filepath the filepath
     */
    public void execute(String filepath) {
        executeSingle(filepath);
    }

    private void executeSingle(String caminhoFile) {
        // Creates an instance of ExternalCSV with a given file name
        ExternalCSV ex = new ExternalCSV(caminhoFile + ".csv");

        // Imports data from the CSV file and creates routes
        ex.importAndCreateRoutes();

        // Retrieves all routes created from the CSV file
        List<Route> allRoutes = ex.getAllRoutes();

        // Generates an image representing all routes
        Graphviz.execute(allRoutes, caminhoFile + "_AllRoutesImage");

        // Finds the minimum spanning tree using Kruskal's algorithm
        List<Route> minimumSpanningTree = KruskalAlgorithm.findMinimumSpanningTree(allRoutes);

        // Writes the minimum spanning tree to CSV file
        ExternalCSV.writeMSTCSV(minimumSpanningTree, caminhoFile);

        // Generates an image representing the minimum spanning tree routes
        Graphviz.execute(minimumSpanningTree, caminhoFile + "_MSTRoutesImage");
    }
}
