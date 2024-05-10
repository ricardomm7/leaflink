package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RunTestsController;

import java.util.Scanner;

/**
 * The RunTestsUI class provides a user interface for running tests.
 */
public class RunTestsUI implements Runnable {
    public static final Scanner sc = new Scanner(System.in);

    private final RunTestsController runTestsController;

    /**
     * Constructs a new RunTestsUI object.
     * Initializes the RunTestsController.
     */
    public RunTestsUI() {
        this.runTestsController = new RunTestsController();
    }

    @Override
    public void run() {
        // Implement the user interface for running tests
    }
}
