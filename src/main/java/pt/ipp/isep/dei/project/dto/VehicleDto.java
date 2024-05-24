package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.VehicleType;

import java.io.Serializable;
import java.time.LocalDate;

public class VehicleDto implements Serializable {
    private String VIN;
    private String brand;
    private String model;
    private VehicleType type;
    private LocalDate registrationDate;
    private String vehiclePlate;
    private double tareWeight;
    private double grossWeight;
    private int currentKm;
    private LocalDate acquisitionDate;
    private int maintenanceFrequency;

    /**
     * Gets VIN.
     *
     * @return The VIN of the vehicle
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Gets brand.
     *
     * @return The brand of the vehicle
     */
    public String getBrand() {
        return brand;
    }


    /**
     * Gets model.
     *
     * @return The model of the vehicle
     */
    public String getModel() {
        return model;
    }


    /**
     * Gets type.
     *
     * @return The type of the vehicle
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Gets vehicle plate.
     *
     * @return The vehicle plate
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }


    /**
     * Gets tare weight.
     *
     * @return The tare weight of the vehicle
     */
    public double getTareWeight() {
        return tareWeight;
    }


    /**
     * Gets gross weight.
     *
     * @return The gross weight of the vehicle
     */
    public double getGrossWeight() {
        return grossWeight;
    }


    /**
     * Gets current km.
     *
     * @return The current kilometers of the vehicle
     */
    public int getCurrentKm() {
        return currentKm;
    }


    /**
     * Gets registration date.
     *
     * @return The registration date of the vehicle
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Gets acquisition date.
     *
     * @return The acquisition date of the vehicle
     */
    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Gets maintenance frequency.
     *
     * @return The maintenance frequency
     */
    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }


    /**
     * Instantiates a new Vehicle DTO class.
     *
     * @param VIN                  the vin of vehicle
     * @param brand                the brand of vehicle
     * @param model                the model of vehicle
     * @param type                 the type of vehicle
     * @param vehiclePlate         the vehicle plate of vehicle
     * @param tareWeight           the tare weight of vehicle
     * @param grossWeight          the gross weight of vehicle
     * @param currentKm            the current km of vehicle
     * @param registrationDate     the registration date of vehicle
     * @param acquisitionDate      the acquisition date of vehicle
     * @param maintenanceFrequency the maintenance frequency of vehicle
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


}

