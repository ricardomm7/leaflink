package pt.ipp.isep.dei.project.domain;


import java.util.Date;

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

    public boolean validateVehicle() {
        if (!brand.matches("[a-zA-Z0-9]+") || !model.matches("[a-zA-Z0-9]+") || !type.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (VIN == null || !VIN.matches("[a-zA-Z0-9]{17}")) {
            return false;
        }

        if (((vehiclePlate == null) || !vehiclePlate.matches("[a-zA-Z0-9]{6}"))) {
            return false;
        }

        if (tareWeight <= 0 || grossWeight <= 0 || currentKm < 0 || maintenanceFrequency <= 0) {
            return false;
        }
        if (!registrationDate.before(acquisitionDate)){
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


    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {this.VIN = VIN;}

    public String getBrand() {return brand;}

    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public Date getRegistrationDate() {return registrationDate;}

    public void setRegistrationDate(Date registrationDate) {this.registrationDate = registrationDate;}

    public Date getAcquisitionDate() {return acquisitionDate;}

    public void setAcquisitionDate(Date acquisitionDate) {this.acquisitionDate = acquisitionDate;}

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {this.type = type;}

}

