package pt.ipp.isep.dei.project.domain;

import java.io.IOException;

/**
 * The Gnuplot class provides a method to execute Cnuplot scripts.
 * Gnuplot is a command-line program that can generate 2D and 3D plots
 */
public abstract class Gnuplot {
    /**
     * Executes the Gnuplot script 'us14.gp'.
     * The script must be located in the current directory
     */
    public static void execute() {
        try {
            // Constructs the command to execute the Gnuplot script.
            String comando = "gnuplot temp/us14.gp";

            //Uses ProcessBuilder to start a new process
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);

            // Starts the process and waits for it to finish
            pb.start().waitFor(); // Waits until the process is finished
        } catch (IOException e) {
            // Handles IOException if there's an error executing Gnuplot
            System.out.println("Error when running Gnuplot: " + e.getMessage());
        } catch (InterruptedException e) {
            // Throws a RunTimeException if the execution is interrupted
            throw new RuntimeException(e);
        }
    }
}
