/**
 * Represents a vehicle with various attributes such as VIN, brand, model, type, registration date,
 * vehicle plate, tare weight, gross weight, current kilometers, acquisition date, and maintenance frequency.
 * This class implements the Serializable interface to allow objects to be serialized and deserialized.
 */
package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.RegisterVehicleUI;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * Retrieves the Vehicle Identification Number (VIN) of the vehicle.
     *
     * @return The VIN of the vehicle.
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Sets the Vehicle Identification Number (VIN) of the vehicle.
     *
     * @param vin The VIN to be set. It must have 17 alphanumeric characters (letters and numbers).
     * @throws IllegalArgumentException If the provided VIN is null, has a length different from 17, or contains non-alphanumeric characters.
     */
    public void setVIN(String vin) {
        if (vin == null || vin.length() != 17 || !vin.matches("[a-zA-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN must have 17 alphanumeric characters (letters and numbers).");
        }
        this.VIN = vin;
    }

    /**
     * Retrieves the brand of the vehicle.
     *
     * @return The brand of the vehicle.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the vehicle.
     *
     * @param brand The brand to be set. It must have only alphanumeric characters (letters and numbers).
     * @throws IllegalArgumentException If the provided brand is null or contains non-alphanumeric characters.
     */
    public void setBrand(String brand) {
        if (brand == null || !brand.matches("^[a-zA-Z0-9- /]+$")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
        this.brand = brand;
    }

    /**
     * Retrieves the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the vehicle.
     *
     * @param model The model to be set. It must have only alphanumeric characters (letters and numbers).
     * @throws IllegalArgumentException If the provided model is null or contains non-alphanumeric characters.
     */
    public void setModel(String model) {
        if (model == null || !model.matches("^[a-zA-Z0-9- /]+$")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
        this.model = model;
    }

    /**
     * Retrieves the type of the vehicle.
     *
     * @return The type of the vehicle.
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Sets the type of the vehicle.
     *
     * @param type The type to be set.
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Retrieves the vehicle plate of the vehicle.
     *
     * @return The vehicle plate of the vehicle.
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    /**
     * Sets the availability status of the vehicle.
     *
     * @param available The availability status to be set.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Sets the vehicle plate of the vehicle.
     *
     * @param vehiclePlate The vehicle plate to be set. It must have 6 alphanumeric characters (letters and numbers).
     * @throws IllegalArgumentException If the provided vehicle plate is null or does not have 6 alphanumeric characters.
     */
    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Vehicle plate must have 6 alphanumeric characters (letters and numbers).\n");
        }
        this.vehiclePlate = vehiclePlate;
    }

    /**
     * Retrieves the tare weight of the vehicle.
     *
     * @return The tare weight of the vehicle.
     */
    public double getTareWeight() {
        return tareWeight;
    }

    /**
     * Sets the tare weight of the vehicle.
     *
     * @param tareWeight The tare weight to be set. It must be greater than zero.
     * @throws IllegalArgumentException If the provided tare weight is less than or equal to zero.
     */
    public void setTareWeight(double tareWeight) {
        if (tareWeight <= 0) {
            throw new IllegalArgumentException("Tare weight must be greater than zero.");
        }
        this.tareWeight = tareWeight;
    }

    /**
     * Retrieves the gross weight of the vehicle.
     *
     * @return The gross weight of the vehicle.
     */
    public double getGrossWeight() {
        return grossWeight;
    }

    /**
     * Sets the gross weight of the vehicle.
     *
     * @param grossWeight The gross weight to be set. It must be greater than zero.
     * @throws IllegalArgumentException If the provided gross weight is less than or equal to zero.
     */
    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
        this.grossWeight = grossWeight;
    }

    /**
     * Retrieves the availability status of the vehicle.
     *
     * @return The availability status of the vehicle.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Retrieves the current kilometers of the vehicle.
     *
     * @return The current kilometers of the vehicle.
     */
    public int getCurrentKm() {
        return currentKm;
    }

    /**
     * Sets the current kilometers of the vehicle.
     *
     * @param currentKm The current kilometers to be set. It must be greater than or equal to zero.
     * @throws IllegalArgumentException If the provided current kilometers is less than zero.
     */
    public void setCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers must be greater than 0 Km.");
        }
        this.currentKm = currentKm;
    }

    /**
     * Retrieves the registration date of the vehicle.
     *
     * @return The registration date of the vehicle.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date of the vehicle.
     *
     * @param registrationDate The registration date to be set.
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Retrieves the acquisition date of the vehicle.
     *
     * @return The acquisition date of the vehicle.
     */
    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date of the vehicle.
     *
     * @param acquisitionDate The acquisition date to be set.
     */
    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Retrieves the maintenance frequency of the vehicle.
     *
     * @return The maintenance frequency of the vehicle.
     */
    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    /**
     * Sets the maintenance frequency of the vehicle.
     *
     * @param maintenanceFrequency The maintenance frequency to be set. It must be greater than zero.
     * @throws IllegalArgumentException If the provided maintenance frequency is less than or equal to zero.
     */
    public void setMaintenanceFrequency(int maintenanceFrequency) {
        if (maintenanceFrequency <= 0) {
            throw new IllegalArgumentException("Maintenance frequency must be greater than 0 Km.");
        }
        this.maintenanceFrequency = maintenanceFrequency;
    }

    /**
     * Constructs a new Vehicle object with the provided parameters.
     *
     * @param VIN                  The Vehicle Identification Number (VIN) of the vehicle.
     * @param brand                The brand of the vehicle.
     * @param model                The model of the vehicle.
     * @param type                 The type of the vehicle.
     * @param registrationDate     The registration date of the vehicle.
     * @param vehiclePlate         The vehicle plate of the vehicle.
     * @param tareWeight           The tare weight of the vehicle.
     * @param grossWeight          The gross weight of the vehicle.
     * @param currentKm            The current kilometers of the vehicle.
     * @param acquisitionDate      The acquisition date of the vehicle.
     * @param maintenanceFrequency The maintenance frequency of the vehicle.
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

    /**
     * Registers a vehicle by validating its attributes.
     *
     * @param vehicle The vehicle to be registered.
     * @return true if the vehicle is valid and can be registered, false otherwise.
     */
    public boolean registerVehicle(Vehicle vehicle) {
        return vehicle.validateVehicle();
    }

    /**
     * Validates the attributes of the vehicle.
     *
     * @return true if the vehicle attributes are valid, false otherwise.
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
     * Validates a given date by checking if it is before the current date.
     *
     * @param date The date to be validated.
     * @return true if the date is before the current date, false otherwise.
     * @throws RegisterVehicleUI.InvalidDateException If the date is after the current date.
     */
    private boolean validateDate(LocalDate date) throws RegisterVehicleUI.InvalidDateException {
        if (date.isAfter(LocalDate.now())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            throw new RegisterVehicleUI.InvalidDateException("Date must be before " + LocalDate.now().format(formatter));
        }
        return true;
    }

    /**
     * Checks if the current object is equal to the specified object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return VIN.equals(vehicle.getVIN());
    }

    /**
     * Calculates the hash code value for the object.
     *
     * @return The hash code value for the object.
     */
    @Override
    public int hashCode() {
        return VIN.hashCode();
    }
}
