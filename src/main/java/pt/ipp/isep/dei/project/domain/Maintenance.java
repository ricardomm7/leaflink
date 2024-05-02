package pt.ipp.isep.dei.project.domain;


import java.util.Date;

public class Maintenance {
    private final String vehiclePlate;
    private final Date date;
    private final int km;

    public Maintenance(String vehiclePlate, Date date, int kilometers)  {
        if (kilometers < 0) {
            throw new IllegalArgumentException("Kilometers cannot be negative");
        }
        if (date == null ) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (vehiclePlate == null || vehiclePlate.isEmpty() || !vehiclePlate.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle plate cannot be null or empty");
        }
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = kilometers;
    }

    public Date getDate() {
        return date;
    }

    public int getKm() {
        return km;
    }

    public String getVehiclePlate(){
        return vehiclePlate;
    }
}
