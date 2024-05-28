package pt.ipp.isep.dei.project.domain;

import java.io.IOException;

/**
 * The Gnuplot class provides a method to execute a Gnuplot script.
 * This class uses the ProcessBuilder to run the Gnuplot script located at a specific path.
 */
public abstract class Gnuplot {

    /**
     * Executes the Gnuplot script.
     * This method constructs the command to run the Gnuplot script and uses ProcessBuilder to start a new process.
     * It waits for the process to finish and handles any exceptions that may occur.
     */
    public static void execute() {
        try {
            // Constructs the command to execute the Gnuplot script.
            String comando = "gnuplot temp/us14.gp";

            // Uses ProcessBuilder to start a new process
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);

            // Starts the process and waits for it to finish
            pb.start().waitFor(); // Waits until the process is finished
        } catch (IOException e) {
            // Handles IOException if there's an error executing Gnuplot
            System.out.println("Error when running Gnuplot: " + e.getMessage());
        } catch (InterruptedException e) {
            // Throws a RuntimeException if the execution is interrupted
            throw new RuntimeException(e);
        }
    }
}
