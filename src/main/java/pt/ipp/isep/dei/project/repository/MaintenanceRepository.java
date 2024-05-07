package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Date;
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
    public void createMaintenance(String plate, Date date, int currentKm) {
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

        for (Maintenance maintenance : maintenanceList) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle != null && maintenance.getVehiclePlate().equalsIgnoreCase(vehicle.getVehiclePlate())) {
                    if (vehicle.getCurrentKm() - maintenance.getKm() >= vehicle.getMaintenanceFrequency()) {
                        hasValidEntry = true;
                        String plate = vehicle.getVehiclePlate();
                        String brand = vehicle.getBrand();
                        String model = vehicle.getModel();
                        int curr_km = vehicle.getCurrentKm();
                        int freq = vehicle.getMaintenanceFrequency();
                        int last = maintenance.getKm();
                        int next = last + freq;
                        reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                                plate, brand, model, curr_km, freq, last, next));
                    }
                }
            }
            reportBuilder.append("\n");
        }

        if (hasValidEntry) {
            return reportBuilder.toString();
        }
        return null;

    }

}