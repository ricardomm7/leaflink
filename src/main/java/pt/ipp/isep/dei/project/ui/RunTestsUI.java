package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.application.controller.RunTestsController;

import java.util.Scanner;

public class RunTestsUI implements Runnable {
    public static final Scanner sc = new Scanner(System.in);

    private final RunTestsController runTestsController;

    public RunTestsUI() {
        this.runTestsController = new RunTestsController();

    }

    @Override
    public void run() {

    }
}
