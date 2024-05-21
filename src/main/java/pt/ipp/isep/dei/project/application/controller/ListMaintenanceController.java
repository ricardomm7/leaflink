package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

/**
 * The ListMaintenanceController class manages the listing the vehicles needing a maintenance.
 * It interacts with the ListMaintenanceUI and with VehicleRepository and MaintenanceRepository
 * to get vehicle-related information.
 */
public class ListMaintenanceController {
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    /**
     * Instantiates a new List maintenance controller.
     */
    public ListMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.maintenanceRepository = repositories.getMaintenanceRepository();
    }

    /**
     * Gets vehicles needing maintenance list.
     *
     * @return The list of vehicles needing maintenance
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Create maintenance report string.
     *
     * @return the string
     */
    public String createMaintenanceReport(List<VehicleDto> vehicles) {
        return maintenanceRepository.createMaintenanceReport(vehicles);
    }
}
