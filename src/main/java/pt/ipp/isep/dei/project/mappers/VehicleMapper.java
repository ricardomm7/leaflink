package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.VehicleDto;

public class VehicleMapper {

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
}
