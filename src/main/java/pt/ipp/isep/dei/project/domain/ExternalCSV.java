package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.application.controller.EvacuationController;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.*;
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
    private List<Route> allRoutes = new ArrayList<>();

    private final String PointsFilePath;

    private List<Point> allPoints = new ArrayList<>();

    /**
     * Constructor to initialize the CSV file path.
     *
     * @param CSVFilepath the path to the CSV file
     */
    public ExternalCSV(String CSVFilepath) {
        this.CSVFilepath = CSVFilepath;
        this.PointsFilePath = null;
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
                    ShowError.showAlert("Import CSV", "This line is mal-formatted", "Line " + line);
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
            ShowError.showAlert("CSV File import", e.getMessage(), null);
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
        try (PrintWriter pW = new PrintWriter(System.getProperty("user.dir") + File.separator + "/goOut/CSV_out")) {
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
            ShowError.showAlert("CSV File writing", e.getMessage(), null);
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
            ShowError.showAlert("CSV File import", "There was an error counting the lines of the CSV file. Please try again later.", null);
        }

        return lineCount;
    }

    public ExternalCSV(String CSVFilePath, String pointsFilePath) {
        this.CSVFilepath = CSVFilePath;
        this.PointsFilePath = pointsFilePath;
    }

    public List<Point> getAllPoints() {
        return allPoints;
    }

    public void readRoutesFromMatrixAndNames() throws IOException {
        List<Point> points = readPoints(this.PointsFilePath);
        double[][] costMatrix = readCostMatrix(this.CSVFilepath, points.size());

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (i != j && costMatrix[i][j] != 0) { // Ignorando auto-loops e rotas de custo zero
                    Point startPoint = points.get(i);
                    Point endPoint = points.get(j);
                    double distance = costMatrix[i][j];
                    Route route = new Route(startPoint, endPoint, distance);
                    routes.add(route);
                }
            }
        }
        this.allRoutes = routes;
    }

    private List<Point> readPoints(String filePath) throws IOException {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] pointNames = line.split(";");
                for (String pointName : pointNames) {
                    if (pointName.trim().toUpperCase().startsWith("AP")) {
                        EvacuationController.index.add(points.size());
                    }
                    points.add(new Point(pointName));
                }
            }
        }
        this.allPoints = points;
        return points;
    }

    private double[][] readCostMatrix(String filePath, int size) throws IOException {
        double[][] costMatrix = new double[size][size];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] costs = line.split(";");
                if (row == 0 && costs[0].charAt(0) == '\uFEFF') {
                    costs[0] = costs[0].substring(1);
                }
                for (int col = 0; col < costs.length; col++) {
                    costMatrix[row][col] = Double.parseDouble(costs[col]);
                }
                row++;
            }
        }
        return costMatrix;
    }

    public static void exportRoutesDijsktraToCSV(List<List<Route>> allShortestPaths) {
        try (FileWriter writer = new FileWriter("goOut/evac/outCSVRoutes.csv")) {
            writer.append("Path").append(";").append("Cost").append("\n");

            for (List<Route> shortestPath : allShortestPaths) {
                if (!shortestPath.isEmpty()) {
                    double totalCost = shortestPath.stream().mapToDouble(Route::getCost).sum();
                    StringBuilder pathBuilder = new StringBuilder();
                    pathBuilder.append("(");

                    // Adicionar o ponto de partida do primeiro segmento
                    pathBuilder.append(shortestPath.get(0).getStartPoint().getID());

                    // Adicionar os pontos finais dos segmentos subsequentes
                    for (Route route : shortestPath) {
                        pathBuilder.append(", ").append(route.getEndPoint().getID());
                    }

                    pathBuilder.append(");").append(totalCost);
                    writer.append(pathBuilder.toString()).append("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
