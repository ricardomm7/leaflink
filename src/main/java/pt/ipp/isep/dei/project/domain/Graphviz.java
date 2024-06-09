package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Graphviz class provides methods to create and execute a Graphviz DOT file.
 * This class allows for the creation of a graph in DOT format and then runs the Graphviz tool to generate an image.
 */
public abstract class Graphviz {

    /**
     * Executes the Graphviz tool to generate an image from a list of routes.
     * This method creates a DOT file representing the graph and runs the Graphviz tool to generate an SVG image.
     *
     * @param tree     the list of routes representing the graph.
     * @param filename the name of the output file (without extension).
     */
    public static void execute(List<Route> tree, String filename) {
        try {
            // Writes the graph in DOT format
            writeGraphvizDotFile(tree);

            // Starts the execution from here
            String caminhoImgFinal = System.getProperty("user.dir") + File.separator + "/goOut/" + filename + ".png";
            String comando = "dot -Tpng " + System.getProperty("user.dir") + File.separator + "/temp/temp_gv_file.dot -o " + caminhoImgFinal;
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.start().waitFor(); // Waits until the process is finished
        } catch (Exception e) {
            ShowError.showAlert("Graphviz", e.getMessage(), null);
        }
    }

    /**
     * Writes the graph in DOT format to a temporary file.
     * This method creates a DOT file representing the graph from the list of routes provided.
     *
     * @param tree the list of routes representing the graph.
     * @throws FileNotFoundException if the file cannot be created or written to.
     */
    private static void writeGraphvizDotFile(List<Route> tree) throws FileNotFoundException {
        PrintWriter pW = new PrintWriter(System.getProperty("user.dir") + File.separator + "/temp/temp_gv_file.dot");
        pW.print("graph G {\n" +
                "fontname=\"Helvetica,Arial,sans-serif\"\n" +
                "node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "layout=dot\n");
        // Writes each route of the graph to the DOT file
        for (Route r : tree) {
            pW.println(r.getStartPoint().getID() + " -- " + r.getEndPoint().getID() + " [label=\"" + r.getCost() + "\"]");
        }
        pW.println("}");
        pW.close();
    }

    /**
     * Executes the Graphviz tool to generate an image from a list of routes using Dijkstra's algorithm.
     * This method creates a DOT file representing the graph and runs the Graphviz tool to generate an SVG image.
     *
     * @param allRoutes     the list of all routes in the graph.
     * @param shortestPath  the list of routes representing the shortest path.
     * @param filename      the name of the output file (without extension).
     */
    public static void executeDijkra(List<Route> allRoutes, List<Route> shortestPath, String filename) {
        try {
            writeGraphvizDotFileDij(allRoutes, shortestPath);

            String caminhoImgFinal = System.getProperty("user.dir") + File.separator + "/goOut/evac/" + filename + ".svg";
            String comando = "dot -Tsvg temp_gv_file.dot -o " + caminhoImgFinal;
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.start().waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao executar o GraphViz: " + e.getMessage());
        }
    }

    /**
     * Writes the graph in DOT format to a temporary file for Dijkstra's algorithm.
     * This method creates a DOT file representing the graph and the shortest path using Dijkstra's algorithm.
     *
     * @param allRoutes     the list of all routes in the graph.
     * @param shortestPath  the list of routes representing the shortest path.
     * @throws IOException if an I/O error occurs.
     */
    private static void writeGraphvizDotFileDij(List<Route> allRoutes, List<Route> shortestPath) throws IOException {
        List<Route> filteredAllRoutes = new ArrayList<>();

        // Filter allRoutes to remove duplicates
        for (Route r : allRoutes) {
            boolean isDuplicate = false;
            for (Route s : filteredAllRoutes) {
                if (s.getStartPoint().getID().equals(r.getEndPoint().getID()) && s.getEndPoint().getID().equals(r.getStartPoint().getID())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate && !shortestPath.contains(r)) {
                filteredAllRoutes.add(r);
            }
        }

        // Remove routes from filteredAllRoutes that are in shortestPath
        filteredAllRoutes.removeIf(r -> {
            for (Route s : shortestPath) {
                if (r.getStartPoint().getID().equals(s.getEndPoint().getID()) && r.getEndPoint().getID().equals(s.getStartPoint().getID())) {
                    return true;
                }
            }
            return false;
        });

        try (PrintWriter pW = new PrintWriter("temp_gv_file.dot")) {
            pW.print("graph G {\n" +
                    "fontname=\"Helvetica,Arial,sans-serif\"\n" +
                    "node [fontname=\"Helvetica,Arial,sans-serif\", color=black, fontcolor=black]\n" +
                    "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                    "layout=dot\n");

            for (Route r : filteredAllRoutes) {
                pW.println(r.getStartPoint().getID() + " -- " + r.getEndPoint().getID() + " [label=\"" + r.getCost() + "\"]");
            }

            for (Route r : shortestPath) {
                pW.println(r.getStartPoint().getID() + " -- " + r.getEndPoint().getID() + " [label=\"" + r.getCost() + "\", color=blue, penwidth=2.0]");
            }

            pW.println("}");
        }
    }
}
