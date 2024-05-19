package pt.ipp.isep.dei.project.domain;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides methods to import data from a CSV file, creates routes and writes MST routes to a new CSV file
 */
public class ExternalCSV {

    // Path to the CSV file
    private final String CSVFilepath;

    // List to store all routes from the CSV file
    private final List<Route> allRoutes = new ArrayList<>();

    // Constructor to initialize the CSV file path
    public ExternalCSV(String CSVFilepath) {
        this.CSVFilepath = CSVFilepath;
    }

    // Method to import data from the CSV file and create routes
    public void importAndCreateRoutes() {
        try (Scanner scanner = new Scanner(new File(this.CSVFilepath))) {
            // Read each line from the CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");

                // Checks if the line contains the correct number of elements
                if (data.length != 3) {
                    // If not, skip this line and proceed to the next one
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                // If the line is correct, it saves the data and creates a route
                Point startPoint = new Point(data[0]);
                Point endPoint = new Point(data[1]);
                double distance = Double.parseDouble(data[2]);

                Route route = new Route(startPoint, endPoint, distance);
                this.allRoutes.add(route);
            }
        } catch (IOException e) {
            // Handle IOException if it's unable to read the CSV file
            System.out.println("Error importing the CSV: " + e.getMessage());
        }
    }

    /**
     * Method to get all routes created from the CSV file
     *
     * @return a copy of the list to prevent external modification
     */
    public List<Route> getAllRoutes() {
        return new ArrayList<>(this.allRoutes);
    }

    /**
     * Writes the Minimum Spanning Tree (MST) routes to a CSV file
     *
     * @param minimumSpanningTree the list of routes forming the MST
     * @param path                the file path to write the MST CSV file
     */
    public static void writeMSTCSV(List<Route> minimumSpanningTree, String path) {
        try (PrintWriter pW = new PrintWriter(System.getProperty("user.dir") + File.separator + "/goOut/" + path + "_out.csv")) {
            // Writes each route of the MST to the CSV file
            double cost = 0;
            for (Route r : minimumSpanningTree) {
                pW.println(r.getStartPoint().getID() + ";" + r.getEndPoint().getID() + ";" + r.getCost());
                cost = cost + r.getCost();
            }
            pW.println();
            pW.println("Accumulated Cost: " + cost);
            pW.close();
        } catch (IOException e) {
            // Handles IOException if unable to write the CSV file
            System.out.println("Error writing CSV MST: " + e.getMessage());
        }
    }

    /**
     * Cpunts the total number of lines in the CSv file
     *
     * @return the number of lines in the CSv file
     */
    public int countLines() {
        int lineCount = 0;

        try (Scanner br = new Scanner(new File(this.CSVFilepath))) {
            // Loop through each line of the CSV file
            while (br.hasNextLine()) {
                br.nextLine();
                lineCount++;
            }
        } catch (IOException e) {
            // Handles IOException if unable to read the CSv file
            System.out.println("There was an error counting lines: " + e.getMessage());
        }

        return lineCount;
    }
}