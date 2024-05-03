package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.List;

public class ListMaintenanceUI implements Runnable {
    private final ListMaintenanceController controller;

    public ListMaintenanceUI() {
        this.controller = new ListMaintenanceController();
    }

    public void listVehiclesNeedingMaintenance() {
        System.out.println("Listing vehicles needing maintenance...");

        List<Vehicle> vehiclesList = controller.getVehiclesNeedingMaintenanceList();

        if (vehiclesList.isEmpty()) {
            System.out.println("No vehicles needing maintenance.");
        } else {
            if (controller.generateMaintenanceReport() != null) {
                System.out.println("Vehicles needing maintenance:");
                System.out.println(controller.generateMaintenanceReport());

            }
            System.out.println("Something went wrong");
        }
    }

    @Override
    public void run() {
        listVehiclesNeedingMaintenance();

    }
}
