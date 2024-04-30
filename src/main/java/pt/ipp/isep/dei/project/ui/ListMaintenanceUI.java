package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.esoft.project.application.Controller.ListMaintenanceController;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

public class ListMaintenanceUI {
    private final ListMaintenanceController controller;

    public ListMaintenanceUI() {
        this.controller = new ListMaintenanceController();
    }

    public void listVehiclesNeedingMaintenance() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Listing vehicles needing maintenance...");

        List<Vehicle> vehiclesList = controller.getVehiclesNeedingMaintenanceList();

        if (vehiclesList.isEmpty()) {
            System.out.println("No vehicles needing maintenance.");
        } else {
            System.out.println("Vehicles needing maintenance:");
            for (Vehicle vehicle : vehiclesList) {
                System.out.println("Plate: " + vehicle.getVehiclePlate());
                System.out.println("Brand: " + vehicle.getBrand());
                System.out.println("Model: " + vehicle.getModel());
                System.out.println("Current Km: " + vehicle.getCurrentKm());
                System.out.println("Maintenance Frequency: " + vehicle.getMaintenanceFrequency() + " km");
                System.out.println("Last Maintenance Km: " + (vehicle.getCurrentKm() - vehicle.getMaintenanceFrequency()) + " km");
                System.out.println("Next Maintenance Km: " + (vehicle.getCurrentKm() + vehicle.getMaintenanceFrequency()) + " km");
                System.out.println();
            }
        }

        System.out.println("Do you want to generate a maintenance report? (yes/no)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            generateMaintenanceReport(vehiclesList);
        }
    }

    private void generateMaintenanceReport(List<Vehicle> vehiclesList) {
        Maintenance maintenance = controller.generateMaintenanceReport(vehiclesList);
        System.out.println("Maintenance report generated successfully on " + maintenance.getData());
    }
}
