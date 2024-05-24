package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MaintenanceDto implements Serializable {
    private String vehiclePlate;
    private LocalDate date;
    private int km;

    public MaintenanceDto(String vehiclePlate, LocalDate date, int km) {
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = km;
    }

    public int getKm() {
        return km;
    }

    public LocalDate getDate() {
        return date;
    }

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
