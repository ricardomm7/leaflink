package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.Date;
import java.util.List;

/**
 * This class represents the controller responsible for registering maintenance.
 */
public class RegisterMaintenanceController {
    private final MaintenanceRepository maintenanceRepository;
    private VehicleRepository vehicleRepository;


    /**
     * Constructs a new RegisterMaintenanceController object.
     * Initializes the MaintenanceRepository and VehicleRepository using the Repositories singleton instance.
     */
    public RegisterMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        maintenanceRepository = repositories.getMaintenanceRepository();
        vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Creates a new maintenance record for the vehicle with the specified plate.
     *
     * @param plate           the plate of the vehicle.
     * @param maintenanceDate the date of the maintenance.
     * @param kilometers      the kilometers of the vehicle at the time of maintenance.
     */
    public void createMaintenance(String plate, Date maintenanceDate, int kilometers) {
        maintenanceRepository.createMaintenance(plate, maintenanceDate, kilometers);
    }

    /**
     * Retrieves a list of plates of all vehicles.
     *
     * @return a list of vehicle plates.
     */
    public List<String> getPlatesList() {
        return vehicleRepository.getVehiclesPlates();
    }
}
