package pt.ipp.isep.dei.project.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Maintenance class represents the object maintenance (Check-up).
 * It holds information such as vehicle plate, date and kilometers (at the moment of the maintenance).
 */
public class Maintenance implements Serializable {
    private final String vehiclePlate;
    private final LocalDate date;
    private final int km;

    /**
     * Instantiates a new Maintenance with verifications and exceptions.
     *
     * @param maintenanceDto the maintenance dto
     */
    public Maintenance(String vehiclePlate, LocalDate date, int currentKm) {
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = currentKm;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintenance that = (Maintenance) o;
        return km == that.km &&
                Objects.equals(vehiclePlate, that.vehiclePlate) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehiclePlate, date, km);
    }
}
