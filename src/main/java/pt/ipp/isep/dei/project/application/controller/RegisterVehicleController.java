package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

/**
 * The RegisterVehicleController class manages the registration of a vehicle within the application.
 * It interacts with the RegisterVehicleUI and with VehicleRepository to store vehicle-related information.
 */
public class RegisterVehicleController {
    private  Repositories repositories;
    private  VehicleRepository vehicleRepository;

    /**
     * Instantiates a new Register vehicle controller.
     */
    public RegisterVehicleController() {

    }


    /**
     * Register vehicle from a vehicle DTO.
     *
     * @param vehicleDto the vehicle dto
     * @return the boolean
     */
    public boolean registerVehicle(VehicleDto vehicleDto) {
        Repositories repositories = Repositories.getInstance();
        VehicleRepository vehicleRepository = repositories.getVehicleRepository();

        if (!vehicleRepository.verifyExistingVehicles(vehicleDto.getVIN(), vehicleDto.getVehiclePlate())) {
            Vehicle vehicle = VehicleMapper.toDomain(vehicleDto);

            return vehicleRepository.registerVehicle(vehicle);

        }

        return false;
    }

}


