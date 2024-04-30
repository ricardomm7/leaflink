package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;

    public RegisterVehicleController() {
        Repositories repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }

    public boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, double currentKm, String acquisitionDate,
                                   int maintenanceFrequency) {
        Vehicle vehicle = new Vehicle(vin, brand, model, type,vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency);


        return vehicleRepository.addVehicle(vehicle) && vehicle.validateVehicle() && vehicleRepository.verifyExistedVehicles(vin,vehiclePlate);
    }
}


