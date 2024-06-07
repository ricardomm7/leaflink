package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.time.LocalDate;
import java.util.List;

/**
 * This class represents the controller responsible for registering maintenance.
 */
public class RegisterMaintenanceController {
    private final MaintenanceRepository maintenanceRepository;
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
     * Creates a new maintenance record with the provided information.
     *
     * @param plate     the plate number of the vehicle
     * @param date      the date of the maintenance
     * @param currentKm the current mileage of the vehicle
     */
    public void createMaintenance(String plate, LocalDate date, int currentKm) {
        try {
            maintenanceRepository.createMaintenance(new MaintenanceDto(plate, date, currentKm));
        }catch (Exception e) {
            ShowError.showAlert("Maintenance", e.getMessage(), "Error");
        }
    }

    /**
     * Retrieves a list of all vehicle plates.
     *
     * @return a list of vehicle plates
     */
    public List<String> getPlatesList() {
        return vehicleRepository.getVehiclesPlates();
    }

    /**
     * Retrieves a list of vehicles needing maintenance.
     *
     * @return a list of vehicles needing maintenance
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }

    /**
     * Retrieves the list of maintenance records.
     *
     * @return the list of maintenance records
     */
    public List<MaintenanceDto> getMaintenanceList() {
        return maintenanceRepository.getMaintenanceList();
    }

    /**
     * Removes a maintenance record.
     *
     * @param plate the plate number of the vehicle
     * @param date  the date of the maintenance record
     */
    public void removeMaintenance(String plate, LocalDate date) {
        maintenanceRepository.removeMaintenance(plate, date);
    }
}
