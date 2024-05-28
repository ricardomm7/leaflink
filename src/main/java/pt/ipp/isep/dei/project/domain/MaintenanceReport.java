package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.io.Serializable;
import java.util.List;

/**
 * The MaintenanceReport class is responsible for creating maintenance reports
 * based on a list of vehicles and their respective maintenance records.
 */
public class MaintenanceReport implements Serializable {

    /**
     * Creates a maintenance report based on the provided vehicle and maintenance lists.
     * The report includes details such as vehicle plate, brand, model, current kilometers,
     * maintenance frequency, last maintenance kilometers, and next maintenance kilometers.
     *
     * @param vehicleDtoList     the list of vehicle data transfer objects
     * @param maintenanceDtoList the list of maintenance data transfer objects
     * @return a formatted maintenance report as a String
     */
    public String createReport(List<VehicleDto> vehicleDtoList, List<MaintenanceDto> maintenanceDtoList) {
        StringBuilder reportBuilder = new StringBuilder();
        boolean hasValidEntry = false;

        reportBuilder.append("Maintenance Report\n");
        reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "Plate", "Brand", "Model", "Curr.Kms", "Freq", "Last", "Next"));

        for (VehicleDto vehicle : vehicleDtoList) {
            if (vehicle != null) {
                MaintenanceDto latestMaintenance = getLatestMaintenance(vehicle, maintenanceDtoList);

                if (latestMaintenance != null) {
                    int lastKm = latestMaintenance.getKm();
                    int currKm = vehicle.getCurrentKm();
                    int freq = vehicle.getMaintenanceFrequency();
                    int nextKm = lastKm + freq;

                    if (currKm >= nextKm * 0.95) {
                        if (currKm > nextKm) {
                            nextKm = currKm;
                        }
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
        return reportBuilder.toString();
    }

    /**
     * Retrieves the latest maintenance record for the specified vehicle from the maintenance list.
     *
     * @param vehicle        the vehicle data transfer object
     * @param maintenanceList the list of maintenance data transfer objects
     * @return the latest maintenance data transfer object for the vehicle, or null if none found
     */
    private static MaintenanceDto getLatestMaintenance(VehicleDto vehicle, List<MaintenanceDto> maintenanceList) {
        MaintenanceDto latestMaintenance = null;

        for (MaintenanceDto maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equalsIgnoreCase(vehicle.getVehiclePlate())) {
                if (latestMaintenance == null || maintenance.getDate().isAfter(latestMaintenance.getDate())) {
                    latestMaintenance = maintenance;
                }
            }
        }
        return latestMaintenance;
    }

    /**
     * Retrieves the latest maintenance record for the specified vehicle from the maintenance list.
     *
     * @param vehicle        the vehicle object
     * @param maintenanceList the list of maintenance objects
     * @return the latest maintenance object for the vehicle, or null if none found
     */
    private static Maintenance getMaintenance(Vehicle vehicle, List<Maintenance> maintenanceList) {
        Maintenance latestMaintenance = null;

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
