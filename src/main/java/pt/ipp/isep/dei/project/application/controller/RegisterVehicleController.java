package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.Date;

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
     * Register the vehicle with all verifications.
     *
     * @param vin                  the vin
     * @param brand                the brand
     * @param model                the model
     * @param type                 the type
     * @param vehiclePlate         the vehicle plate
     * @param tareWeight           the tare weight
     * @param grossWeight          the gross weight
     * @param currentKm            the current km
     * @param registrationDate     the registration date
     * @param acquisitionDate      the acquisition date
     * @param maintenanceFrequency the maintenance frequency
     * @return the boolean
     */
    public boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, int currentKm, Date registrationDate, Date acquisitionDate,
                                   int maintenanceFrequency) {

        if (!vehicleRepository.verifyExistingVehicles(vin, vehiclePlate)) {

            return vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                    currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        }

        return false;
    }

}


