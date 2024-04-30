package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegisterVehicleUI {
    private final RegisterVehicleController controller;

    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
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
        double currentKm = Double.parseDouble(scanner.nextLine());

        Date registrationDate;
        do{
            System.out.println("Enter registration date (DD/MM/YYYY):");
            registrationDate = parseDate(scanner.nextLine());
        }while (registrationDate==null);

        Date acquisitionDate;
        do {
            System.out.println("Enter acquisition date (DD/MM/YYYY):");
            acquisitionDate = parseDate(scanner.nextLine());
        } while (acquisitionDate == null);

        System.out.println("Enter maintenance frequency (in km):");
        int maintenanceFrequency = Integer.parseInt(scanner.nextLine());

        System.out.println("\nConfirms Vehicle data:\n");
        System.out.println("Vin: " + vin + "\nBrand: " + brand + "\nModel: " + model + "\nVehicle Plate: " + vehiclePlate + "\nTare Weight: "
                + tareWeight + "\nGross Weight: " + grossWeight + "\nCurrent Kilometers: " + currentKm + "\nRegistration Date"+ registrationDate +"\nAcquisition date: "
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

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format! Please enter date in DD/MM/YYYY format.");
            return null;
        }
    }
}