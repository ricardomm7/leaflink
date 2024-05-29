/**
 * This class represents the user interface for analyzing park usage.
 */
package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.AnalyseParkUseController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyseParkUseUI implements Runnable {
    private String filepath;

    /**
     * Executes the park usage analysis.
     *
     * @throws FileNotFoundException if the specified file is not found.
     */
    public void execute() throws FileNotFoundException {
        AnalyseParkUseController.runsPythonScript(filepath);
    }

    /**
     * Prompts the user to upload a file.
     */
    public void uploadFile() {
        System.out.print("Enter the file path: ");
        Scanner sc = new Scanner(System.in);
        this.filepath = sc.nextLine();
    }

    /**
     * Runs the user interface for analyzing park usage.
     */
    @Override
    public void run() {
        uploadFile();
        try {
            execute();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
