package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements Serializable {

    /**
     * To dto Vehicle dto.
     *
     * @param vehicle the skill
     * @return the vehicle dto
     */
    public static VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getVIN(), vehicle.getBrand(), vehicle.getModel(), vehicle.getType(),
                vehicle.getRegistrationDate(), vehicle.getVehiclePlate(), vehicle.getTareWeight(), vehicle.getGrossWeight(),
                vehicle.getCurrentKm(), vehicle.getAcquisitionDate(), vehicle.getMaintenanceFrequency());
    }

    /**
     * To domain Vehicle.
     *
     * @param vehicleDto the vehicle dtp
     * @return the vehicle domain
     */
    public static Vehicle toDomain(VehicleDto vehicleDto) {
        return new Vehicle(vehicleDto.getVIN(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getType(),
                vehicleDto.getRegistrationDate(), vehicleDto.getVehiclePlate(), vehicleDto.getTareWeight(), vehicleDto.getGrossWeight(),
                vehicleDto.getCurrentKm(), vehicleDto.getAcquisitionDate(), vehicleDto.getMaintenanceFrequency());
    }

    public static List<VehicleDto> toDtoList(List<Vehicle> vehicleList) {
        List<VehicleDto> result = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = VehicleMapper.toDto(vehicle);

            result.add(vehicleDto);
        }

        return result;
    }

    public static List<Vehicle> toDomainList(List<VehicleDto> vehicleList) {
        List<Vehicle> result = new ArrayList<>();
        for (VehicleDto vehicle : vehicleList) {
            Vehicle vehicleDom = VehicleMapper.toDomain(vehicle);

            result.add(vehicleDom);
        }

        return result;
    }
}
