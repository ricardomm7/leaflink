package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MaintenanceRepository class manages the storage and retrieval of maintenance within the application.
 */
public class MaintenanceRepository {
    private final List<Maintenance> maintenanceList;

    /**
     * Instantiates a new Maintenance repository.
     */
    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
    }

    /**
     * Create maintenance.
     *
     * @param plate     the plate
     * @param date      the date
     * @param currentKm the current km
     */
    public void createMaintenance(String plate, LocalDate date, int currentKm) {
        Maintenance maintenance = new Maintenance(plate, date, currentKm);
        addMaintenance(maintenance);
    }

    /**
     * Add maintenance.
     *
     * @param maintenance the maintenance
     */
    public void addMaintenance(Maintenance maintenance) {
        maintenanceList.add(maintenance);
    }

    /**
     * Gets maintenance list.
     *
     * @return the maintenance list
     */
    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }


    /**
     * Create maintenance report string.
     *
     * @param vehicleList the vehicle list
     * @return the string
     */
    public String createMaintenanceReport(List<Vehicle> vehicleList) {
        List<Maintenance> maintenanceList = getMaintenanceList();
        StringBuilder reportBuilder = new StringBuilder();
        boolean hasValidEntry = false;

        reportBuilder.append("Maintenance Report\n");
        reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "Plate", "Brand", "Model", "Curr.Kms", "Freq", "Last", "Next"));

        for (Vehicle vehicle : vehicleList) {
            if (vehicle != null) {
                Maintenance latestMaintenance = getLatestMaintenance(vehicle, maintenanceList);

                if (latestMaintenance != null) {
                    int lastKm = latestMaintenance.getKm();
                    int currKm = vehicle.getCurrentKm();
                    int freq = vehicle.getMaintenanceFrequency();
                    int nextKm = lastKm + freq;

                    if (currKm >= nextKm * 0.95) {
                        hasValidEntry = true;
                        String plate = vehicle.getVehiclePlate();
                        String brand = vehicle.getBrand();
                        String model = vehicle.getModel();

                        reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                                plate, brand, model, currKm, freq, lastKm, nextKm));
                    }
                }
            }
        }

        if (hasValidEntry) {
            return reportBuilder.toString();
        }

        return null;
    }

    private static Maintenance getLatestMaintenance(Vehicle vehicle, List<Maintenance> maintenanceList) {
        Maintenance latestMaintenance = null;

        // Find the latest maintenance for the vehicle
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equalsIgnoreCase(vehicle.getVehiclePlate())) {
                if (latestMaintenance == null || maintenance.getDate().isAfter(latestMaintenance.getDate())) {
                    latestMaintenance = maintenance;
                }
            }
        }
        return latestMaintenance;
    }


    /**
     * Get the last maintenance of a vehicle
     *
     * @param vehicle
     * @param maintenanceList
     * @return
     */
    private static Maintenance getMaintenance(Vehicle vehicle, List<Maintenance> maintenanceList) {
        Maintenance latestMaintenance = null;

        // Find the latest maintenance for the vehicle
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equalsIgnoreCase(vehicle.getVehiclePlate())) {
                if (latestMaintenance == null || maintenance.getDate().isAfter(latestMaintenance.getDate())) {
                    latestMaintenance = maintenance;
                }
            }
        }
        return latestMaintenance;
    }

}

