package pt.ipp.isep.dei.project.domain;


import pt.ipp.isep.dei.project.ui.RegisterVehicleUI;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Vehicle class represents the object Vehicle.
 * It holds information such as VIN, brand, model, type, plate,tare Weight, gross weight, currently kilometer, registration date,
 * acquisition date and maintenance frequency.
 */
public class Vehicle implements Serializable {
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
    private boolean isAvailable;


    /**
     * Gets VIN.
     *
     * @return The VIN of the vehicle
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Sets VIN of the vehicle with validation exception.
     *
     * @param vin New VIN introduced for the vehicle
     */
    public void setVIN(String vin) {
        if (vin == null || vin.length() != 17 || !vin.matches("[a-zA-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN must have 17 alphanumeric characters (letters and numbers).");
        }
        this.VIN = vin;
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
     * Sets brand of the vehicle with validation exception.
     *
     * @param brand New brand introduced for the vehicle
     */
    public void setBrand(String brand) {
        if (brand == null || !brand.matches("^[a-zA-Z0-9- /]+$")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
        this.brand = brand;
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
     * Sets model with validation exception.
     *
     * @param model New model of the vehicle
     */
    public void setModel(String model) {
        if (model == null || !model.matches("^[a-zA-Z0-9- /]+$")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
        this.model = model;
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
     * Sets type with validation exception.
     *
     * @param type New type of vehicle
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Gets vehicle plate.
     *
     * @return The vehicle plate
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Sets vehicle plate with validation exception.
     *
     * @param vehiclePlate New vehicle plate
     */
    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Vehicle plate must have 6 alphanumeric characters (letters and numbers).\n");
        }
        this.vehiclePlate = vehiclePlate;
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
     * Sets tare weight with validation exception.
     *
     * @param tareWeight New tare weight of the vehicle
     */
    public void setTareWeight(double tareWeight) {
        if (tareWeight <= 0) {
            throw new IllegalArgumentException("Tare weight must be greater than zero.");
        }
        this.tareWeight = tareWeight;
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
     * Sets gross weight with validation exception.
     *
     * @param grossWeight New gross weight of the vehicle
     */
    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
        this.grossWeight = grossWeight;
    }

    public boolean isAvailable() {
        return isAvailable;
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
     * Sets current km with validation exception.
     *
     * @param currentKm New current Kilometer of the vehicle
     */
    public void setCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers must be greater than 0 Km.");
        }
        this.currentKm = currentKm;
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
     * Sets registration date with validation exception.
     *
     * @param registrationDate New registration date of the vehicle
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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
     * Sets acquisition date with validation exception.
     *
     * @param acquisitionDate New acquisition date of the vehicle
     */
    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
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
     * Sets maintenance frequency with validation exception.
     *
     * @param maintenanceFrequency New maintenance frequency of the vehicle
     */
    public void setMaintenanceFrequency(int maintenanceFrequency) {
        if (maintenanceFrequency <= 0) {
            throw new IllegalArgumentException("Maintenance frequency must be greater than 0 Km.");
        }
        this.maintenanceFrequency = maintenanceFrequency;
    }

    /**
     * Instantiates a new Vehicle class.
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
    public Vehicle(String VIN, String brand, String model, VehicleType type, LocalDate registrationDate, String vehiclePlate, double tareWeight, double grossWeight,
                   int currentKm, LocalDate acquisitionDate, int maintenanceFrequency) {
        this.isAvailable = true;
        setVIN(VIN);
        setBrand(brand);
        setModel(model);
        setType(type);
        setRegistrationDate(registrationDate);
        setVehiclePlate(vehiclePlate);
        setTareWeight(tareWeight);
        setGrossWeight(grossWeight);
        setCurrentKm(currentKm);
        setAcquisitionDate(acquisitionDate);
        setMaintenanceFrequency(maintenanceFrequency);
    }

    public boolean registerVehicle(Vehicle vehicle) {

        return vehicle.validateVehicle();
    }

    /**
     * Validate all vehicle data.
     *
     * @return boolean (True if it is valid / False if it is invalid)
     */
    private boolean validateVehicle() {
        if (!brand.matches("[a-zA-Z0-9]+") || !model.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (VIN == null || !VIN.matches("[a-zA-Z0-9]{17}+")) {
            return false;
        }

        if (((vehiclePlate == null) || !vehiclePlate.matches("[a-zA-Z0-9]{6}+"))) {
            return false;
        }

        int year = registrationDate.getYear();

        if (year >= 2020) {
            if (!vehiclePlate.matches("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{2}$")) {
                return false;
            }
        } else if (year >= 2005) {
            if (!vehiclePlate.matches("^\\d{2}[a-zA-Z]{2}\\d{2}$")) {
                return false;
            }
        } else {
            if (!vehiclePlate.matches("^\\d{4}[a-zA-Z]{2}$")) {
                return false;
            }
        }
        if (tareWeight <= 0 || grossWeight <= 0 || currentKm < 0 || maintenanceFrequency <= 0) {
            return false;
        }
        if (acquisitionDate.isAfter(LocalDate.now()) || registrationDate.isAfter(LocalDate.now())) {
            return false;
        }

        return registrationDate.isBefore(this.acquisitionDate);
    }

    /**
     * Validates the date parameter. It must exist.
     *
     * @param date
     */
    private boolean validateDate(LocalDate date) throws RegisterVehicleUI.InvalidDateException {
        if (date.isAfter(LocalDate.now())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            throw new RegisterVehicleUI.InvalidDateException("Date must be before " + LocalDate.now().format(formatter));
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return VIN.equals(vehicle.getVIN());
    }

    @Override
    public int hashCode() {
        return VIN.hashCode();
    }
}