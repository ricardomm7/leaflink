package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Maintenance class represents a maintenance record for a vehicle,
 * containing the vehicle's plate number, the date of the maintenance,
 * and the current kilometers at the time of the maintenance.
 */
public class Maintenance implements Serializable {
    private final String vehiclePlate;
    private final LocalDate date;
    private final int km;

    /**
     * Constructs a new Maintenance object with the specified vehicle plate, date, and kilometers.
     *
     * @param vehiclePlate the plate number of the vehicle
     * @param date         the date of the maintenance
     * @param currentKm    the kilometers at the time of the maintenance
     */
    public Maintenance(String vehiclePlate, LocalDate date, int currentKm) {
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = currentKm;
    }

    /**
     * Gets the date of the maintenance.
     *
     * @return the date of the maintenance
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the kilometers at the time of the maintenance.
     *
     * @return the kilometers at the time of the maintenance
     */
    public int getKm() {
        return km;
    }

    /**
     * Gets the plate number of the vehicle.
     *
     * @return the plate number of the vehicle
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    /**
     * Compares this Maintenance object to the specified object. The result is true if and only if
     * the argument is not null and is a Maintenance object that has the same vehicle plate,
     * date, and kilometers as this object.
     *
     * @param o the object to compare this Maintenance object against
     * @return true if the given object represents a Maintenance object equivalent to this maintenance, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintenance that = (Maintenance) o;
        return km == that.km &&
                Objects.equals(vehiclePlate, that.vehiclePlate) &&
                Objects.equals(date, that.date);
    }

    /**
     * Returns a hash code value for this Maintenance object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(vehiclePlate, date, km);
    }
}
