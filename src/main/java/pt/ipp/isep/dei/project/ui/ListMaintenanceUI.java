package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.util.List;


/**
 * The ListMaintenanceUI class provides a user interface to get a list of vehicles needing check-up/maintenance.
 */
public class ListMaintenanceUI implements Runnable {
    private final ListMaintenanceController controller;

    /**
     * Instantiates a new List maintenance ui.
     */
    public ListMaintenanceUI() {
        this.controller = new ListMaintenanceController();
    }

    /**
     * List vehicles needing maintenance UI.
     */
    public void listVehiclesNeedingMaintenance() {
        System.out.println("Listing vehicles needing maintenance...");

        List<VehicleDto> vehiclesList = controller.getVehiclesNeedingMaintenanceList();

        if (vehiclesList.isEmpty()) {
            System.out.println("No vehicles needing maintenance.");
        } else {
            if (controller.createMaintenanceReport(vehiclesList) != null) {
                System.out.println("Vehicles needing maintenance:");
                System.out.println(controller.createMaintenanceReport(vehiclesList));

            }
        }
    }

    @Override
    public void run() {
        listVehiclesNeedingMaintenance();

    }
}
