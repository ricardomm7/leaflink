package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

/**
 * The type List maintenance controller.
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
    public List<Vehicle> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Generate maintenance report.
     */
    public String generateMaintenanceReport() {
        return maintenanceRepository.createMaintenanceReport(getVehiclesNeedingMaintenanceList());
    }
}
