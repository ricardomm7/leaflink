package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * This class represents the controller responsible for registering maintenance.
 */
public class RegisterMaintenanceController {
    /**
     * The Maintenance repository.
     */
    private final MaintenanceRepository maintenanceRepository;
    /**
     * The Vehicle repository.
     */
    private final VehicleRepository vehicleRepository;


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
     * Create maintenance.
     *
     * @param plate     the plate
     * @param date1     the date
     * @param currentKm the current km
     */
    public void createMaintenance(String plate, LocalDate date1, int currentKm) {
        maintenanceRepository.createMaintenance(new MaintenanceDto(plate, date1, currentKm));
    }

    /**
     * Retrieves a list of all vehicle's plates.
     *
     * @return a list of vehicle plates.
     */
    public List<String> getPlatesList() {
        return vehicleRepository.getVehiclesPlates();
    }

    /**
     * Gets vehicles needing maintenance list.
     *
     * @return the vehicles needing maintenance list
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Gets maintenance list.
     *
     * @return the maintenance list
     */
    public List<MaintenanceDto> getMaintenanceList() {
        return maintenanceRepository.getMaintenanceList();
    }

    /**
     * Remove maintenance.
     *
     * @param plate the plate
     */
    public void removeMaintenance(String plate, LocalDate date) {
        maintenanceRepository.removeMaintenance(plate, date);
    }
}
