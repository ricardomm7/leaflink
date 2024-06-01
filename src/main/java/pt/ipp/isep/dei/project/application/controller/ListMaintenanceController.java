package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

/**
 * The ListMaintenanceController class manages the listing of vehicles needing maintenance.
 * It interacts with the ListMaintenanceUI and with VehicleRepository and MaintenanceRepository
 * to get vehicle-related information.
 */
public class ListMaintenanceController {
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    /**
     * Constructs a new ListMaintenanceController object.
     * Initializes the repositories for vehicles and maintenance.
     */
    public ListMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
        maintenanceRepository = repositories.getMaintenanceRepository();
    }

    /**
     * Generates a maintenance report by retrieving vehicles that need maintenance and creating the report.
     */
    public void generateMaintenanceReport() {
        List<VehicleDto> vehicleDtosNeedingMaintenance = getVehiclesNeedingMaintenanceList();
        createMaintenanceReport(vehicleDtosNeedingMaintenance);
    }

    /**
     * Retrieves the list of vehicles that need maintenance.
     *
     * @return The list of VehicleDto objects representing vehicles needing maintenance
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Creates a maintenance report for the given list of vehicles.
     *
     * @param vehicles The list of VehicleDto objects representing vehicles needing maintenance
     * @return The maintenance report as a string
     */
    public String createMaintenanceReport(List<VehicleDto> vehicles) {
        return maintenanceRepository.createMaintenanceReport(vehicles);
    }
}
