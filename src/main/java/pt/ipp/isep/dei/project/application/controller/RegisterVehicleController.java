package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.time.LocalDate;
import java.util.List;

/**
 * The RegisterVehicleController class handles the logic for registering new vehicles.
 */
public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a new RegisterVehicleController object.
     * Initializes the VehicleRepository instance.
     */
    public RegisterVehicleController() {
        Repositories repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }


    /**
     * Register vehicle boolean.
     *
     * @param vin                  the vin
     * @param brand                the brand
     * @param model                the model
     * @param type                 the type
     * @param registrationDate     the registration date
     * @param vehiclePlate         the vehicle plate
     * @param tareWeight           the tare weight
     * @param grossWeight          the gross weight
     * @param currentKm            the current km
     * @param acquisitionDate      the acquisition date
     * @param maintenanceFrequency the maintenance frequency
     * @return the boolean
     */
    public boolean registerVehicle(String vin, String brand, String model, VehicleType type, LocalDate registrationDate, String vehiclePlate, double tareWeight, double grossWeight, int currentKm, LocalDate acquisitionDate, int maintenanceFrequency) {
        try {
            VehicleDto vehicleDto = new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight, currentKm, acquisitionDate, maintenanceFrequency);
            return vehicleRepository.registerVehicle(vehicleDto);
        } catch (Exception e) {
            ShowError.showAlert("Vehicle", e.getMessage(), "Error");
            return false;
        }
    }

    /**
     * Gets vehicle list.
     *
     * @return the vehicle list
     */
    public List<VehicleDto> getVehicleList() {
        return vehicleRepository.getVehicleList();
    }

    /**
     * Remove vehicle.
     *
     * @param plate the plate
     */
    public void removeVehicle(String plate) {
        vehicleRepository.removeVehicle(plate);
    }
}
