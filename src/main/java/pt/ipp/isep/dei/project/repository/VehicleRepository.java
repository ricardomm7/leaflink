package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VehicleRepository {
    private final List<Vehicle> vehicleList;

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public boolean verifyExistingVehicles(String vin, String vehiclePlate) {
        // Verify if params are null
        if (vin == null || vehiclePlate == null) {
            throw new IllegalArgumentException("VIN and vehicle plate cannot be null.");
        }
        // Verify the size of the params
        if (vin.length() != 17 || vehiclePlate.length() != 6) {
            throw new IllegalArgumentException("VIN must be exactly 17 characters and vehicle plate must be exactly 6 characters.");
        }

        // Verify existing vehicle
        for (Vehicle vehicle : getVehicleList()) {
            if (vin.equalsIgnoreCase(vehicle.getVIN()) || vehiclePlate.equalsIgnoreCase(vehicle.getVehiclePlate())) {
                return true;
            }
        }
        return false;
    }

    public Boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, int currentKm, Date registrationDate, Date acquisitionDate,
                                   int maintenanceFrequency) {

        Vehicle vehicle = new Vehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        if (vehicle.validateVehicle()) {
            addVehicle(vehicle);
            return true;
        }
        return false;
    }


    public List<Vehicle> getVehiclesNeedingMaintenanceList(List<Maintenance> maintenanceList) {
        List<Vehicle> vehiclesNeedingMaintenance = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            double currentKm = vehicle.getCurrentKm();
            double maintenanceFrequency = vehicle.getMaintenanceFrequency();
            double lastMaintenanceKm = getLastMaintenanceKm(vehicle.getVehiclePlate(), maintenanceList);

            if (lastMaintenanceKm == -1 || currentKm - lastMaintenanceKm >= maintenanceFrequency) {
                vehiclesNeedingMaintenance.add(vehicle);
            }
        }
        return vehiclesNeedingMaintenance;
    }

    private double getLastMaintenanceKm(String vehiclePlate, List<Maintenance> maintenanceList) {
        double lastMaintenanceKm = -1; // Valor padrão para indicar que não há registro de manutenção

        // Lógica para obter o km da última manutenção para o veículo com a placa especificada
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
    }

    public Vehicle getVehicleByPlate(String plate) {
        Vehicle vehicle = null;
        for (Vehicle v : vehicleList) {
            if (v.getVehiclePlate().equalsIgnoreCase(plate)) {
                vehicle = v;
            }
        }
        return vehicle;
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicleList.remove(vehicle);
    }


}

