package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

public class ListMaintenanceUI implements Runnable {
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
            controller.generateMaintenanceReport();
        }
    }

    @Override
    public void run() {
        listVehiclesNeedingMaintenance();

    }
}
