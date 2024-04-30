package pt.ipp.isep.dei.esoft.project.application.Controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class ListMaintenanceController {
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    public ListMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.maintenanceRepository = repositories.getMaintenanceRepository();
    }

    public List<Vehicle> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehicleNeedingMaintenance();
    }

    public void generateMaintenanceReport() {
        maintenanceRepository.createMaintenanceReport();
    }
}
