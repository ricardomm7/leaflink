package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;


public class VehicleRepository {
    private final List<Vehicle> vehicleList;

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }



    public boolean verifyExistedVehicles( String vin, String vehiclePlate) {
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

    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }
}

