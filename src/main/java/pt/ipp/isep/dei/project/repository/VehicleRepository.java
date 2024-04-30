package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;


public class VehicleRepository {
    private final List<Vehicle> vehicleList;

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }



    public boolean verifyExistingVehicles(String vin, String vehiclePlate) {
        for (Vehicle vehicle : getVehicleList()) {
            if (!vin.equalsIgnoreCase(vehicle.getVIN()) || !vehiclePlate.equalsIgnoreCase(vehicle.getVehiclePlate()) ) {
                return false;
            }
        }
        return true;
    }


    public boolean addVehicle(Vehicle vehicle) {
        return vehicleList.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicleList.remove(vehicle);
    }

    public List<Vehicle> getVehicleNeedingMaintenance(){
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
        List<Maintenance> maintenanceList = Repositories.getInstance().getMaintenanceRepository().getMaintenanceList();

        // Lógica para obter o km da última manutenção para o veículo com a placa especificada
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
    }

    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }
}

