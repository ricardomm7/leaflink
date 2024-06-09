package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.ExternalCSV;
import pt.ipp.isep.dei.project.domain.Gnuplot;
import pt.ipp.isep.dei.project.domain.KruskalAlgorithm;
import pt.ipp.isep.dei.project.domain.Route;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The type Run tests controller.
 */
public class RunTestsController {
    /**
     * Execute.
     *
     * @param caminhosFile the caminhos file
     * @throws FileNotFoundException the file not found exception
     */
    public void execute(List<String> caminhosFile) throws FileNotFoundException {
        executeMultiple(caminhosFile);
        Gnuplot.execute();
    }

    /**
     * Executes the tests on multiple CSV files, recording execution times for each.
     *
     * @param caminhosFile the list of CSV file paths (without the ".csv" extension)
     * @throws FileNotFoundException if any of the CSV files cannot be found
     */
    private void executeMultiple(List<String> caminhosFile) throws FileNotFoundException {

        // Initializes a 2D array to store execution times for each file
        double[][] executionTimes = new double[caminhosFile.size()][2];

        // Counter to keep track of the current file being processed
        int counter = 0;

        // Iterates through each file path
        for (String caminhoFile : caminhosFile) {
            System.out.println("Current file: " + (counter + 1));

            // Creates an instance of ExternalCSV with the current file path
            ExternalCSV ex = new ExternalCSV(caminhoFile + ".csv");

            // Imports data from the CSV file and creates routes
            ex.importAndCreateRoutes();

            // Retrieves all routes created from the CSV file
            List<Route> allRoutes = ex.getAllRoutes();

            // Records the start time of the execution
            double startTime = System.currentTimeMillis();

            // Finds the minimum spanning tree using Kruskal's algorithm
            List<Route> minimumSpanningTree = KruskalAlgorithm.findMinimumSpanningTree(allRoutes);

            // Records the end time of the execution
            double endTime = System.currentTimeMillis();

            // Calculates the execution time in seconds
            executionTimes[counter][0] = ex.countLines();
            executionTimes[counter][1] = ((endTime - startTime));

            // Moves to the next file
            counter++;
        }

        //Writes execution times to a text file
        PrintWriter pw = new PrintWriter("goOut\\US14_Data.csv");
        for (int i = 0; i < caminhosFile.size(); i++) {
            pw.println(executionTimes[i][0] + ";" + executionTimes[i][1]);
        }
        pw.close();
    }
}
