package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterMaintenanceController;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The RegisterMaintenanceUI class provides a user interface for registering maintenance for vehicles.
 */
public class RegisterMaintenanceUI implements Runnable {
    private static Scanner sc = new Scanner(System.in);
    private static RegisterMaintenanceController controller;

    /**
     * Constructs a new RegisterMaintenanceUI object.
     * Initializes the RegisterMaintenanceController.
     */
    public RegisterMaintenanceUI() {
        controller = new RegisterMaintenanceController();
    }

    /**
     * Registers maintenance for a selected vehicle.
     */
    public void RegisterMaintenance() {
        System.out.println("Select vehicle to register a maintenance.");
        List<String> plates = controller.getPlatesList();
        // present list of vehicles
        for (int i = 0; i < plates.size(); i++) {
            System.out.println((i + 1) + ". " + plates.get(i));
        }
        int plateIdx = sc.nextInt();
        sc.nextLine();
        String plate = plates.get(plateIdx - 1);

        System.out.println("Insert the date of the maintenance (DD-MM-YYYY).");
        String date = sc.nextLine();
        Date date1 = parseDate(date);

        System.out.println("Insert the current Km of the vehicle.");
        int currentKm = sc.nextInt();
        sc.nextLine();

        // display all data and request confirmation
        System.out.println("Do you want to register this maintenance?");
        System.out.println("Vehicle plate: " + plate);
        System.out.println("Date: " + date1.toString());
        System.out.println("Current Km: " + currentKm);

        // Confirm registration
        System.out.println("\nDo you want to register this maintenance? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            controller.createMaintenance(plate, date1, currentKm);
            System.out.println("Maintenance successfully registered!");
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
        String[] parts = dateString.split("[/-]");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Adjust month value to be 0-based (0 for January)
        month--;

        // Create a Date object
        return new Date(year - 1900, month, day);
    }

    @Override
    public void run() {
        RegisterMaintenance();
    }
}
