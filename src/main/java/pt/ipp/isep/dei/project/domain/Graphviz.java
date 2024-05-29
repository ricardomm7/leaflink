package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
            String caminhoImgFinal = System.getProperty("user.dir") + File.separator + "/goOut/" + filename + ".svg";
            String comando = "dot -Tsvg " + System.getProperty("user.dir") + File.separator + "/temp/temp_gv_file.dot -o " + caminhoImgFinal;
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
}
