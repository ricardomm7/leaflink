
package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

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
     * Registers a new vehicle based on the provided VehicleDto.
     *
     * @param vehicleDto the VehicleDto containing the vehicle data
     * @return true if the vehicle is registered successfully, false otherwise
     */
    public boolean registerVehicle(VehicleDto vehicleDto) {
        if (!vehicleRepository.verifyExistingVehicles(vehicleDto.getVIN(), vehicleDto.getVehiclePlate())) {
            return vehicleRepository.registerVehicle(vehicleDto);
        }
        return false;
    }
}
