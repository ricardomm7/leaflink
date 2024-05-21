package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.time.LocalDate;

/**
 * The RegisterVehicleController class manages the registration of a vehicle within the application.
 * It interacts with the RegisterVehicleUI and with VehicleRepository to store vehicle-related information.
 */
public class RegisterVehicleController {
    private final Repositories repositories;
    private final VehicleRepository vehicleRepository;

    /**
     * Instantiates a new Register vehicle controller.
     */
    public RegisterVehicleController() {
        repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }


    /**
     * Register vehicle from a vehicle DTO.
     *
     * @param vehicleDto the vehicle dto
     * @return the boolean
     */
    public boolean registerVehicle(VehicleDto vehicleDto) {

        if (!vehicleRepository.verifyExistingVehicles(vehicleDto.getVIN(), vehicleDto.getVehiclePlate())) {

            Vehicle vehicle = VehicleMapper.toDomain(vehicleDto);
            return vehicleRepository.registerVehicle(vehicle);

        }

        return false;
    }

}


