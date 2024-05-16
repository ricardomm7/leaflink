package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.WaterIrrigationController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaterIrrigationUI implements Runnable{
    private String filepath;

    /**
     * Executes the park usage analysis.
     *
     * @throws FileNotFoundException if the specified file is not found.
     */
    public void execute() throws FileNotFoundException {
        WaterIrrigationController g1 = new WaterIrrigationController();
        g1.execute(filepath);
    }

    /**
     * Prompts the user to upload a file.
     */
    public void uploadFile() {
        System.out.print("Enter the file path for the minimum cost route calculation: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        this.filepath = input;
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
