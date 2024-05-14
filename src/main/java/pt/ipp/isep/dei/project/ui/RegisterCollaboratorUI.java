package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.project.domain.Job;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The RegisterCollaboratorUI class provides a user interface for registering a new collaborator.
 */
public class RegisterCollaboratorUI implements Runnable {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Registers a new collaborator based on user input.
     */
    public void registerCollaborator() {
        RegisterCollaboratorController rc = new RegisterCollaboratorController();
        System.out.println("Enter collaborator's name:");
        String name = sc.nextLine();

        System.out.println("Enter collaborator's birthdate (DD/MM/YYYY):");
        String birthdate = sc.nextLine();
        Date birthdate1 = parseDate(birthdate);

        System.out.println("Enter collaborator's mobile contact:");
        int contactMobile = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter collaborator's email:");
        String email = sc.nextLine();

        System.out.println("Enter collaborator's address:");
        String address = sc.nextLine();

        System.out.println("Enter collaborator's zip code (format: ####-###):");
        String zipCode = sc.nextLine();

        System.out.println("Enter collaborator's city:");
        String city = sc.nextLine();

        System.out.println("Enter collaborator's document type:");
        String documentType = sc.nextLine();

        System.out.println("Enter collaborator's identification number:");
        String identificationNumber = sc.nextLine();

        System.out.println("Enter collaborator's admission date (DD/MM/YYYY):");
        String admissionDate = sc.nextLine();
        Date admissionDate1 = parseDate(admissionDate);

        System.out.println("Enter collaborator's taxpayer number:");
        int taxpayerNumber = sc.nextInt();
        sc.nextLine(); // consume the newline character

        System.out.println("Select a collaborator's job title:");
        List<Job> jobTitles = rc.getJobs();
        for (int i = 0; i < jobTitles.size(); i++) {
            System.out.println((i + 1) + ". " + jobTitles.get(i).getTitle());
        }
        // Prompt user to select a job title
        int jobIndex = sc.nextInt();
        sc.nextLine(); // consume the newline character
        // Ensure the selected index is valid
        if (jobIndex < 1 || jobIndex > jobTitles.size()) {
            System.out.println("Invalid selection. Please select a valid index.");
            return;
        }
        Job job = jobTitles.get(jobIndex - 1);

        // Display all entered information for confirmation
        System.out.println("\nPlease review the entered information:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate1);
        System.out.println("Mobile Contact: " + contactMobile);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Zip Code: " + zipCode);
        System.out.println("City: " + city);
        System.out.println("Document Type: " + documentType);
        System.out.println("Identification Number: " + identificationNumber);
        System.out.println("Admission Date: " + admissionDate1);
        System.out.println("Taxpayer Number: " + taxpayerNumber);
        System.out.println("Job Title: " + job.getTitle());

        // Confirm registration
        System.out.println("\nDo you want to register this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            rc.createCLB(name, birthdate1, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate1, job);
            System.out.println("Collaborator successfully registered!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }

    /**
     * Parses a string representation of a date into a Date object.
     *
     * @param dateString the string representation of the date.
     * @return the Date object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    private Date parseDate(String dateString) {
        String[] parts = dateString.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Adjust month value to be 0-based (0 for January)
        month--;

        // Create a Date object
        return new Date(year, month, day);
    }

    @Override
    public void run() {
        registerCollaborator();
    }

}
