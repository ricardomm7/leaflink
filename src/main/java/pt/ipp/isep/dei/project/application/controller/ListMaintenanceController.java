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
    private VehicleRepository vehicleRepository;
    private MaintenanceRepository maintenanceRepository;

    /**
     * Instantiates a new List maintenance controller.
     */
    public ListMaintenanceController() {
    }

    /**
     * Get all the repositories and vehicleDto needing maintenance
     * Generate maintenance report.
     */
    public void GenerateMaintenanceReport() {
        ;
        List<VehicleDto> vehicleDtosNeedingMaintenance = getVehiclesNeedingMaintenanceList();
        createMaintenanceReport(vehicleDtosNeedingMaintenance);

    }

    /**
     * Gets vehicles needing maintenance list.
     *
     * @return The list of vehicles needing maintenance
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList() {
        Repositories repositories = Repositories.getInstance();
        VehicleRepository vehicleRepository = repositories.getVehicleRepository();
        MaintenanceRepository maintenanceRepository = repositories.getMaintenanceRepository();

        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Create maintenance report string.
     *
     * @return the string
     */
    public String createMaintenanceReport(List<VehicleDto> vehicles) {
        Repositories repositories = Repositories.getInstance();
        MaintenanceRepository maintenanceRepository = repositories.getMaintenanceRepository();
        return maintenanceRepository.createMaintenanceReport(vehicles);
    }

}
