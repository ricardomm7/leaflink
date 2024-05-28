package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The VehicleRepository class is responsible for managing the collection of Vehicle objects.
 * It provides methods for retrieving available vehicles, registering new vehicles, and managing vehicle availability.
 */
public class VehicleRepository implements Serializable {
    private final List<Vehicle> vehicleList;

    /**
     * Constructs a new VehicleRepository object and initializes the vehicle list.
     */
    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    /**
     * Gets the list of available VehicleDto objects.
     *
     * @return the list of available VehicleDto objects
     */
    public List<VehicleDto> getAvailableVehicleList() {
        List<Vehicle> availableVehicle = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.isAvailable()) {
                availableVehicle.add(v);
            }
        }
        return VehicleMapper.toDtoList(availableVehicle);
    }

    /**
     * Gets the list of all VehicleDto objects.
     *
     * @return the list of all VehicleDto objects
     */
    public List<VehicleDto> getVehicleList() {
        return VehicleMapper.toDtoList(vehicleList);
    }

    /**
     * Registers a new vehicle based on the provided VehicleDto.
     *
     * @param dto the VehicleDto containing the vehicle data
     * @return true if the vehicle is registered successfully, false otherwise
     */
    public boolean registerVehicle(VehicleDto dto) {
        Vehicle vehicle = VehicleMapper.toDomain(dto);
        if (!verifyExistingVehicles(vehicle.getVehiclePlate(), vehicle.getVIN())) {
            if (vehicle.registerVehicle(vehicle)) {
                addVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a vehicle to the repository.
     *
     * @param vehicle the Vehicle object to be added
     */
    private void addVehicle(Vehicle vehicle) {
        if (getAvailableVehicleList().contains(VehicleMapper.toDto(vehicle))) {
            throw new IllegalArgumentException("Vehicle already exists.");
        }
        vehicleList.add(vehicle);
    }

    /**
     * Verifies if a vehicle with the given VIN or license plate already exists in the repository.
     *
     * @param vin          the VIN (Vehicle Identification Number) of the vehicle
     * @param vehiclePlate the license plate of the vehicle
     * @return true if a vehicle with the given VIN or license plate exists, false otherwise
     */
    public boolean verifyExistingVehicles(String vin, String vehiclePlate) {
        for (VehicleDto vehicle : getAvailableVehicleList()) {
            if (vin.equalsIgnoreCase(vehicle.getVIN()) || vehiclePlate.equalsIgnoreCase(vehicle.getVehiclePlate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the list of license plates for all vehicles in the repository.
     *
     * @return the list of license plates
     */
    public List<String> getVehiclesPlates() {
        List<String> plates = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            plates.add(vehicle.getVehiclePlate());
        }
        return plates;
    }

    /**
     * Gets the list of VehicleDto objects that need maintenance based on the provided maintenance list.
     *
     * @param maintenanceList the list of MaintenanceDto objects
     * @return the list of VehicleDto objects that need maintenance
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
     * Gets the kilometer reading of the last maintenance for a given vehicle license plate.
     *
     * @param vehiclePlate    the license plate of the vehicle
     * @param maintenanceList the list of MaintenanceDto objects
     * @return the kilometer reading of the last maintenance, or -1 if no maintenance record is found
     */
    private double getLastMaintenanceKm(String vehiclePlate, List<MaintenanceDto> maintenanceList) {
        double lastMaintenanceKm = -1; // Default value to indicate no maintenance record

        // Logic to get the km of the last maintenance for the vehicle with the specified license plate
        for (MaintenanceDto maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
    }

    /**
     * Sets the availability of a vehicle at the specified index in the vehicle list.
     *
     * @param vehicleIndex the index of the vehicle in the vehicle list
     * @param isAvailable  the availability status to be set (true for available, false for unavailable)
     */
    public void setVehicleAvailability(int vehicleIndex, Boolean isAvailable) {
        vehicleList.get(vehicleIndex).setAvailable(isAvailable);
    }
}
