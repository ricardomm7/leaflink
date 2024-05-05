package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.Date;

public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;
    private final Repositories repositories;

    public RegisterVehicleController() {
        repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }


    public boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, int currentKm,Date registrationDate, Date acquisitionDate,
                                   int maintenanceFrequency) {

        if (!vehicleRepository.verifyExistingVehicles(vin,vehiclePlate)) {

            return vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                    currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        }

        return false;
    }

}


