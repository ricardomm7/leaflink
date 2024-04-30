package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Maintenance;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    private final List<Maintenance> maintenanceList;
    private final VehicleRepository vehicleRepository;

    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
        this.vehicleRepository = new VehicleRepository();
    }

    public void createMaintenance(String plate, String date, int currentKm) {
        Maintenance maintenance = new Maintenance(plate, date, currentKm);
        addMaintenance(maintenance);

    }


    public void createMaintenanceReport() {
        writeReportToFile(getMaintenanceList());
    }

    private void writeReportToFile(List<Maintenance> maintenance) {
        String fileName = "maintenance_report_" + LocalDate.now() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Maintenance Report\n");

            for (Maintenance maintenance1 : maintenance) {
                writer.write("Generated on: " + maintenance1.getData() + "\n\n");
                writer.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        "Plate", "Brand", "Model", "Curr.Kms", "Freq", "Last", "Next"));
                for (Vehicle vehicle : vehicleRepository.getVehicleList()) {
                    if (vehicle.getVehiclePlate().equals(maintenance1.getVehiclePlate())) {
                        writer.write(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                                vehicle.getVehiclePlate(), vehicle.getBrand(), vehicle.getModel(),
                                vehicle.getCurrentKm(), vehicle.getMaintenanceFrequency(),
                                vehicle.getCurrentKm() - vehicle.getMaintenanceFrequency(),
                                vehicle.getCurrentKm() + vehicle.getMaintenanceFrequency()));
                    }
                }
            }
            System.out.println("Maintenance report created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error creating maintenance report: " + e.getMessage());
        }
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void addMaintenance(Maintenance maintenance) {
        maintenanceList.add(maintenance);
    }

    public List<String> getPlatesRegistered(List<Vehicle> vehicles) {
        List<String> plates = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            plates.add(vehicle.getVehiclePlate());
        }
        return plates;
    }
}
