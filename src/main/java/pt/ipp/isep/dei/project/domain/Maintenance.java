package pt.ipp.isep.dei.project.domain;


import java.time.LocalDate;
import java.util.Date;

/**
 * The Maintenance class represents the object maintenance (Check-up).
 * It holds information such as vehicle plate, date and kilometers (at the moment of the maintenance).
 */
public class Maintenance {
    private final String vehiclePlate;
    private final LocalDate date;
    private final int km;

    /**
     * Instantiates a new Maintenance with verifications and exceptions.
     *
     * @param vehiclePlate the vehicle plate
     * @param date         the date
     * @param kilometers   the kilometers
     */
    public Maintenance(String vehiclePlate, LocalDate date, int kilometers) {
        if (kilometers < 0) {
            throw new IllegalArgumentException("Kilometers cannot be negative");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (vehiclePlate == null || vehiclePlate.isEmpty() || !vehiclePlate.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle plate cannot be null or empty");
        }
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = kilometers;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets km.
     *
     * @return the km
     */
    public int getKm() {
        return km;
    }

    /**
     * Get vehicle plate string.
     *
     * @return the string
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }
}
