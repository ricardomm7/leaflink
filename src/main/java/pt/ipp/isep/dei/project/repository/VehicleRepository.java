package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * The VehicleRepository class manages the storage and retrieval of Vehicles within the application.
 */
public class VehicleRepository {
    private final List<Vehicle> vehicleList;

    /**
     * Instantiates a new Vehicle repository with an empty list of vehicles.
     */
    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    /**
     * Gets vehicle list.
     *
     * @return the vehicle list
     */
    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }


    /**
     * Register vehicle boolean.
     *
     * @param vehicle the vehicle
     * @return the boolean
     */
    public Boolean registerVehicle(Vehicle vehicle) {

        if (vehicle.validateVehicle()) {
            addVehicle(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Add vehicle.
     *
     * @param vehicle The vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        if (!vehicle.validateVehicle()) {
            throw new IllegalArgumentException("Invalid vehicle.");
        }
        if (getVehicleList().contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle already exists.");
        }
        vehicleList.add(vehicle);
    }

    /**
     * Verify existing vehicles boolean.
     *
     * @param vin          The vin of the vehicle
     * @param vehiclePlate The vehicle plate of the vehicle
     * @return the boolean (True if vehicle exists, False otherwise)
     */
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

    /**
     * Get vehicles plates list.
     *
     * @return the list
     */
    public List<String> getVehiclesPlates() {
        List<String> plates = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            plates.add(vehicle.getVehiclePlate());
        }
        return plates;
    }

    /**
     * Gets vehicles needing maintenance list.
     *
     * @param maintenanceList The maintenance list
     * @return The list of vehicles needing maintenance
     */
    public List<VehicleDto> getVehiclesNeedingMaintenanceList(List<MaintenanceDto> maintenanceList) {
        List<VehicleDto> vehiclesNeedingMaintenance = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            double currentKm = vehicle.getCurrentKm();
            double maintenanceFrequency = vehicle.getMaintenanceFrequency();
            double lastMaintenanceKm = getLastMaintenanceKm(vehicle.getVehiclePlate(), maintenanceList);

            double nextMaintenanceKm = lastMaintenanceKm + maintenanceFrequency;


            if (lastMaintenanceKm == -1 || currentKm >= nextMaintenanceKm * 0.95) {
                vehiclesNeedingMaintenance.add(VehicleMapper.toDto(vehicle));
            }
        }
        return vehiclesNeedingMaintenance;
    }

    /**
     * Get the last maintenance km registered of a vehicle
     *
     * @param vehiclePlate    The vehicle plate
     * @param maintenanceList The list of the maintenance
     * @return The km of the last maintenance
     */

    public double getLastMaintenanceKm(String vehiclePlate, List<MaintenanceDto> maintenanceList) {
        double lastMaintenanceKm = -1; // Valor padrão para indicar que não há registro de manutenção

        // Lógica para obter o km da última manutenção para o veículo com a placa especificada
        for (MaintenanceDto maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
    }

    /**
     * Remove vehicle.
     *
     * @param vehicle The vehicle
     */
    public void removeVehicle(Vehicle vehicle) {
        vehicleList.remove(vehicle);
    }
}

