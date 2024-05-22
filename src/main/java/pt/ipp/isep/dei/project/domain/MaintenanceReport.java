package pt.ipp.isep.dei.project.domain;


import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.util.List;


/**
 * The Maintenance class represents the object maintenance (Check-up).
 * It holds information such as vehicle plate, date and kilometers (at the moment of the maintenance).
 */
public class MaintenanceReport {
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

    private static MaintenanceDto getLatestMaintenance(VehicleDto vehicle, List<MaintenanceDto> maintenanceList) {
        MaintenanceDto latestMaintenance = null;

        // Find the latest maintenance for the vehicle
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
