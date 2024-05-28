package pt.ipp.isep.dei.project.domain;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ExternalCSV class provides methods for importing routes from a CSV file
 * and exporting the minimum spanning tree (MST) to a CSV file.
 * It also provides functionality to count the lines in the CSV file.
 */
public class ExternalCSV {

    /**
     * Path to the CSV file.
     */
    private final String CSVFilepath;

    /**
     * List to store all routes from the CSV file.
     */
    private final List<Route> allRoutes = new ArrayList<>();

    /**
     * Constructor to initialize the CSV file path.
     *
     * @param CSVFilepath the path to the CSV file
     */
    public ExternalCSV(String CSVFilepath) {
        this.CSVFilepath = CSVFilepath;
    }

    /**
     * Method to import data from the CSV file and create routes.
     * It reads the CSV file line by line, parses the data, and creates Route objects.
     */
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
     * Returns a list of all routes imported from the CSV file.
     *
     * @return a list of all routes
     */
    public List<Route> getAllRoutes() {
        return new ArrayList<>(this.allRoutes);
    }

    /**
     * Writes the minimum spanning tree (MST) to a CSV file.
     *
     * @param minimumSpanningTree the list of routes that form the MST
     * @param path                the path where the CSV file will be saved
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
        } catch (IOException e) {
            // Handles IOException if unable to write the CSV file
            System.out.println("Error writing CSV MST: " + e.getMessage());
        }
    }

    /**
     * Counts the number of lines in the CSV file.
     *
     * @return the number of lines in the CSV file
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
            // Handles IOException if unable to read the CSV file
            System.out.println("There was an error counting lines: " + e.getMessage());
        }

        return lineCount;
    }
}
