package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaintenanceRepository {
    private final List<Maintenance> maintenanceList;

    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
    }

    public void createMaintenance(String plate, Date date, int currentKm) {
        Maintenance maintenance = new Maintenance(plate, date, currentKm);
        addMaintenance(maintenance);

    }

    public void addMaintenance(Maintenance maintenance) {
        maintenanceList.add(maintenance);
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }


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
            reportBuilder.append("\n");
        }

        if (hasValidEntry) {
            return reportBuilder.toString();
        }
        return null;

    }

}