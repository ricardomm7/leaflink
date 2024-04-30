package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateJobController;

import java.util.Scanner;

public class CreateJobUI {
    private static Scanner sc = new Scanner(System.in);

    public void createNewJob() {
        CreateJobController jc = new CreateJobController();
        System.out.println("What is the job title?");
        String title = sc.nextLine();

        System.out.println("You want to create a job with the name " + title + "? (Y/N)");
        String decis = sc.nextLine();
        if (decis.trim().equals("Y")) {
            jc.createJob(title);
            System.out.println("Operation successfully completed!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }

}
