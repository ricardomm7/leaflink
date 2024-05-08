package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.MaintenanceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.Date;
import java.util.List;

public class RegisterMaintenanceController {
    private final MaintenanceRepository maintenanceRepository;
    private VehicleRepository vehicleRepository;


    public RegisterMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        maintenanceRepository = repositories.getMaintenanceRepository();
        vehicleRepository = repositories.getVehicleRepository();
    }

    public void createMaintenance(String plate, Date maintenanceDate, int kilometers) {
        maintenanceRepository.createMaintenance(plate, maintenanceDate, kilometers);

    }

    public List<String> getPlatesList(){
        return vehicleRepository.getVehiclesPlates();
    }


}
