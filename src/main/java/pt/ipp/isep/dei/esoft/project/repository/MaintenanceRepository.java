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

    public List<Vehicle> getVehiclesNeedingMaintenance(List<Vehicle> vehicleList) {
        List<Vehicle> vehiclesNeedingMaintenance = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            double currentKm = vehicle.getCurrentKm();
            double maintenanceFrequency = vehicle.getMaintenanceFrequency();
            double lastMaintenanceKm = getLastMaintenanceKm(vehicle.getVehiclePlate());

            // Se não houver registro de manutenção anterior, consideramos o último km de manutenção como 0
            if (lastMaintenanceKm == -1 || currentKm - lastMaintenanceKm >= maintenanceFrequency) {
                vehiclesNeedingMaintenance.add(vehicle);
            }
        }
        return vehiclesNeedingMaintenance;
    }

    private double getLastMaintenanceKm(String vehiclePlate) {
        double lastMaintenanceKm = -1; // Valor padrão para indicar que não há registro de manutenção

        // Lógica para obter o km da última manutenção para o veículo com a placa especificada
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
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
