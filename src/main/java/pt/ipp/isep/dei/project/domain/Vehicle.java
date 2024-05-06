package pt.ipp.isep.dei.project.domain;


import java.util.Date;



/**
 *
 * The Vehicle class represents the object Vehicle that belongs to an organization.
 * It holds information such as VIN, brand, model, type, plate,tare Weight, gross weight, currently kilometer, registration date,
 * acquisition date and maintenance frequency.
 *
 */
public class Vehicle {
    private String VIN;
    private String brand;
    private String model;
    private String type;
    private String vehiclePlate;
    private double tareWeight;
    private double grossWeight;
    private int currentKm;
    private Date registrationDate;
    private Date acquisitionDate;
    private int maintenanceFrequency;


    /**
     * Gets VIN.
     *
     * @return The VIN of the vehicle
     */
    public String getVIN() {return VIN;}

    /**
     * Sets VIN of the vehicle.
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
    public String getBrand() {return brand;}

    /**
     * Sets brand of the vehicle.
     *
     * @param brand New brand introduced for the vehicle
     */
    public void setBrand(String brand) {
        if (brand == null || !brand.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
        this.brand = brand;
    }

    /**
     * Gets model.
     *
     * @return The model of the vehicle
     */
    public String getModel() {return model;}

    /**
     * Sets model.
     *
     * @param model New model of the vehicle
     */
    public void setModel(String model) {
        if (model == null || !model.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
        this.model = model;
    }

    /**
     * Gets type.
     *
     * @return The type of the vehicle
     */
    public String getType() {return type;}


    /**
     * Sets type.
     *
     * @param type New type of vehicle
     */
    public void setType(String type) {
        if (type == null || !type.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle type must have only alphanumeric characters (letters and numbers).");
        }
        this.type = type;
    }

    /**
     * Gets vehicle plate.
     *
     * @return The vehicle plate
     */
    public String getVehiclePlate() {return vehiclePlate;}

    /**
     * Sets vehicle plate.
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
    public double getTareWeight() {return tareWeight;}

    /**
     * Sets tare weight.
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
    public double getGrossWeight() {return grossWeight;}

    /**
     * Sets gross weight.
     *
     * @param grossWeight New gross weight of the vehicle
     */
    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
        this.grossWeight = grossWeight;
    }

    /**
     * Gets current km.
     *
     * @return The current kilometers of the vehicle
     */
    public int getCurrentKm() {return currentKm;}

    /**
     * Sets current km.
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
    public Date getRegistrationDate() {return registrationDate;}

    /**
     * Sets registration date.
     *
     * @param registrationDate New registration date of the vehicle
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Gets acquisition date.
     *
     * @return The acquisition date of the vehicle
     */
    public Date getAcquisitionDate() {return acquisitionDate;}

    /**
     * Sets acquisition date.
     *
     * @param acquisitionDate New acquisition date of the vehicle
     */
    public void setAcquisitionDate(Date acquisitionDate) {this.acquisitionDate = acquisitionDate;}

    /**
     * Gets maintenance frequency.
     *
     * @return The maintenance frequency
     */
    public int getMaintenanceFrequency() {return maintenanceFrequency;}

    /**
     * Sets maintenance frequency.
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
    public Vehicle(String VIN, String brand, String model, String type, String vehiclePlate, double tareWeight, double grossWeight,
                   int currentKm, Date registrationDate, Date acquisitionDate, int maintenanceFrequency) {

        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.vehiclePlate = vehiclePlate;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registrationDate = registrationDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;


    }

    /**
     * Validate all vehicle data.
     *
     * @return boolean (True if it is valid / False if it is invalid)
     */
    public boolean validateVehicle() {
        if (!brand.matches("[a-zA-Z0-9]+") || !model.matches("[a-zA-Z0-9]+") || !type.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (VIN == null || !VIN.matches("[a-zA-Z0-9]{17}+")) {
            return false;
        }

        if (((vehiclePlate == null) || !vehiclePlate.matches("[a-zA-Z0-9]{6}+"))) {
            return false;
        }

        if (tareWeight <= 0 || grossWeight <= 0 || currentKm < 0 || maintenanceFrequency <= 0) {
            return false;
        }
        if (!registrationDate.before(acquisitionDate)) {
            return false;

        }
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return VIN.equals(vehicle.VIN);
    }

    @Override
    public int hashCode() {
        return VIN.hashCode();
    }



}

