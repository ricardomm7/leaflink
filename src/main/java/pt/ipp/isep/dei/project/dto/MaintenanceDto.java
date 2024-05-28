
package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The MaintenanceDto class represents a data transfer object for the Maintenance domain object.
 * It encapsulates the data related to a maintenance record and provides methods to access this data.
 */
public class MaintenanceDto implements Serializable {
    private final String vehiclePlate;
    private final LocalDate date;
    private final int km;

    /**
     * Constructs a new MaintenanceDto object with the provided data.
     *
     * @param vehiclePlate the license plate of the vehicle
     * @param date         the date of the maintenance
     * @param km           the kilometer reading at the time of maintenance
     */
    public MaintenanceDto(String vehiclePlate, LocalDate date, int km) {
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = km;
    }

    /**
     * Gets the kilometer reading at the time of maintenance.
     *
     * @return the kilometer reading
     */
    public int getKm() {
        return km;
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
     * Gets the license plate of the vehicle.
     *
     * @return the license plate of the vehicle
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceDto that = (MaintenanceDto) o;
        return km == that.km &&
                Objects.equals(vehiclePlate, that.vehiclePlate) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehiclePlate, date, km);
    }
}
