package pt.ipp.isep.dei.project.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class Graphviz {

    /**
     * Exectes the GraphViz process to generate a visualization of the graph.
     *
     * @param tree     the list of routes representing the graph
     * @param filename the filename for the output image
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
        } catch (IOException e) {
            System.out.println("Error running GraphViz: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the graph in DOT format to a temporary file
     *
     * @param tree the list of routes representing the graph
     * @throws FileNotFoundException if the file to write is not found
     */
    private static void writeGraphvizDotFile(List<Route> tree) throws FileNotFoundException {
        PrintWriter pW = new PrintWriter(System.getProperty("user.dir") + File.separator + "/temp/temp_gv_file.dot");
        pW.print("graph G {\n" +
                "fontname=\"Helvetica,Arial,sans-serif\"\n" +
                "node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "layout=dot\n");
        // Writes aeach route of the graph to the DOT FILE
        for (Route r : tree) {
            pW.println(r.getStartPoint().getID() + " -- " + r.getEndPoint().getID() + " [label=\"" + r.getCost() + "\"]");
        }
        pW.println("}");
        pW.close();
    }
}
