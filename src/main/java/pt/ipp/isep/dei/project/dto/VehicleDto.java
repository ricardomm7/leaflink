
package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.VehicleType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The VehicleDto class represents a data transfer object for the Vehicle domain object.
 * It encapsulates the data related to a vehicle and provides methods to access this data.
 */
public class VehicleDto implements Serializable {
    private final String VIN;
    private final String brand;
    private final String model;
    private final VehicleType type;
    private final LocalDate registrationDate;
    private final String vehiclePlate;
    private final double tareWeight;
    private final double grossWeight;
    private final int currentKm;
    private final LocalDate acquisitionDate;
    private final int maintenanceFrequency;

    /**
     * Gets the VIN (Vehicle Identification Number) of the vehicle.
     *
     * @return The VIN of the vehicle
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Gets the brand of the vehicle.
     *
     * @return The brand of the vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the model of the vehicle.
     *
     * @return The model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the type of the vehicle.
     *
     * @return The type of the vehicle
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Gets the license plate of the vehicle.
     *
     * @return The vehicle plate
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    /**
     * Gets the tare weight (unladen weight) of the vehicle.
     *
     * @return The tare weight of the vehicle
     */
    public double getTareWeight() {
        return tareWeight;
    }

    /**
     * Gets the gross weight (maximum operating weight) of the vehicle.
     *
     * @return The gross weight of the vehicle
     */
    public double getGrossWeight() {
        return grossWeight;
    }

    /**
     * Gets the current kilometer reading of the vehicle.
     *
     * @return The current kilometers of the vehicle
     */
    public int getCurrentKm() {
        return currentKm;
    }

    /**
     * Gets the registration date of the vehicle.
     *
     * @return The registration date of the vehicle
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Gets the acquisition date of the vehicle.
     *
     * @return The acquisition date of the vehicle
     */
    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Gets the maintenance frequency (in kilometers) of the vehicle.
     *
     * @return The maintenance frequency
     */
    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    /**
     * Constructs a new VehicleDto object with the provided data.
     *
     * @param VIN                  the VIN (Vehicle Identification Number) of the vehicle
     * @param brand                the brand of the vehicle
     * @param model                the model of the vehicle
     * @param type                 the type of the vehicle
     * @param vehiclePlate         the license plate of the vehicle
     * @param tareWeight           the tare weight (unladen weight) of the vehicle
     * @param grossWeight          the gross weight (maximum operating weight) of the vehicle
     * @param currentKm            the current kilometer reading of the vehicle
     * @param registrationDate     the registration date of the vehicle
     * @param acquisitionDate      the acquisition date of the vehicle
     * @param maintenanceFrequency the maintenance frequency (in kilometers) of the vehicle
     */
    public VehicleDto(String VIN, String brand, String model, VehicleType type, LocalDate registrationDate, String vehiclePlate, double tareWeight, double grossWeight,
                      int currentKm, LocalDate acquisitionDate, int maintenanceFrequency) {

        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.registrationDate = registrationDate;
        this.vehiclePlate = vehiclePlate;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleDto vehicleDto = (VehicleDto) o;
        return VIN.equals(vehicleDto.VIN);
    }

    @Override
    public int hashCode() {
        return VIN.hashCode();
    }

    @Override
    public String toString() {
        return vehiclePlate + " | " + brand;
    }
}
