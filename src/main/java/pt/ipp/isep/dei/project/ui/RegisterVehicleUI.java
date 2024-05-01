package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class RegisterVehicleUI implements Runnable {
    private final RegisterVehicleController controller;

    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
        registerVehicle();
    }

    public void registerVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter VIN:");
        String vin = scanner.nextLine();

        System.out.println("Enter Brand name:");
        String brand = scanner.nextLine();

        System.out.println("Enter Model name:");
        String model = scanner.nextLine();

        System.out.println("Enter vehicle type:");
        String type = scanner.nextLine();

        System.out.println("Enter vehicle plate:");
        String vehiclePlate = scanner.nextLine();

        System.out.println("Enter tare weight:");
        double tareWeight = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter gross weight:");
        double grossWeight = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter current km:");
        int currentKm = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter registration date (DD/MM/YYYY):");
        String registrationDateStr = scanner.nextLine();
        Date registrationDate = parseDate(registrationDateStr);

        System.out.println("Enter acquisition date (DD/MM/YYYY):");
        String acquisitionDateStr = scanner.nextLine();
        Date acquisitionDate = parseDate(acquisitionDateStr);

        System.out.println("Enter maintenance frequency (in km):");
        int maintenanceFrequency = Integer.parseInt(scanner.nextLine());

        System.out.println("\nConfirms Vehicle data:\n");
        System.out.println("Vin: " + vin + "\nBrand: " + brand + "\nModel: " + model + "\nVehicle Plate: " + vehiclePlate + "\nTare Weight: "
                + tareWeight + "\nGross Weight: " + grossWeight + "\nCurrent Kilometers: " + currentKm + "\nRegistration: "+ registrationDate +"\nAcquisition date: "
                + acquisitionDate + "\nMaintenance Frequency: " + maintenanceFrequency);

        System.out.println("\n Types 'Yes' to confirm data:");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (controller.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                    currentKm, registrationDate, acquisitionDate, maintenanceFrequency)) {
                System.out.println("Vehicle registered successfully!");
            } else {
                System.out.println("Failed to register vehicle.");
            }
        } else {
            System.out.println("Vehicle registration canceled.");
        }
    }
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
        return new Date(year - 1900, month, day);
    }

    @Override
    public void run() {
        registerVehicle();

    }
}