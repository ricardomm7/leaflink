package pt.ipp.isep.dei.project.ui;

import java.util.Scanner;

import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;

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

        System.out.println("Enter acquisition date (DD-MM-YYYY):");
        String acquisitionDateStr = scanner.nextLine();

        System.out.println("Enter maintenance frequency (in km):");
        int maintenanceFrequency = Integer.parseInt(scanner.nextLine());

        boolean success = controller.registerVehicle(vin, brand,model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDateStr, maintenanceFrequency);

        if (success) {
            System.out.println("Vehicle registered successfully!");
        } else {
            System.out.println("Failed to register vehicle.");
        }
    }


}
