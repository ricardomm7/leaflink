package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The VehicleMapper class is responsible for mapping between the Vehicle domain object and the VehicleDto data transfer object.
 * It provides methods to convert a Vehicle object to a VehicleDto object and vice versa, as well as methods to convert
 * lists of Vehicle objects to lists of VehicleDto objects and vice versa.
 */
public class VehicleMapper implements Serializable {

    /**
     * Converts a Vehicle domain object to a VehicleDto data transfer object.
     *
     * @param vehicle the Vehicle domain object to be converted
     * @return the corresponding VehicleDto data transfer object
     */
    public static VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getVIN(), vehicle.getBrand(), vehicle.getModel(), vehicle.getType(),
                vehicle.getRegistrationDate(), vehicle.getVehiclePlate(), vehicle.getTareWeight(), vehicle.getGrossWeight(),
                vehicle.getCurrentKm(), vehicle.getAcquisitionDate(), vehicle.getMaintenanceFrequency());
    }

    /**
     * Converts a VehicleDto data transfer object to a Vehicle domain object.
     *
     * @param vehicleDto the VehicleDto data transfer object to be converted
     * @return the corresponding Vehicle domain object
     */
    public static Vehicle toDomain(VehicleDto vehicleDto) {
        if (vehicleDto == null) {
            return null;
        }
        try {
            return new Vehicle(vehicleDto.getVIN(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getType(),
                    vehicleDto.getRegistrationDate(), vehicleDto.getVehiclePlate(), vehicleDto.getTareWeight(), vehicleDto.getGrossWeight(),
                    vehicleDto.getCurrentKm(), vehicleDto.getAcquisitionDate(), vehicleDto.getMaintenanceFrequency());
        } catch (Exception e) {
            ShowError.showAlert("Vehicle", e.getMessage(), "Error when setting the vehicle attributes.");
            throw e;
        }
    }

    /**
     * Converts a list of Vehicle domain objects to a list of VehicleDto data transfer objects.
     *
     * @param vehicleList the list of Vehicle domain objects to be converted
     * @return the corresponding list of VehicleDto data transfer objects
     */
    public static List<VehicleDto> toDtoList(List<Vehicle> vehicleList) {
        List<VehicleDto> result = new ArrayList<>();
        if (vehicleList == null) {
            return result;
        }
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = VehicleMapper.toDto(vehicle);
            result.add(vehicleDto);
        }
        return result;
    }

    /**
     * Converts a list of VehicleDto data transfer objects to a list of Vehicle domain objects.
     *
     * @param vehicleList the list of VehicleDto data transfer objects to be converted
     * @return the corresponding list of Vehicle domain objects
     */
    public static List<Vehicle> toDomainList(List<VehicleDto> vehicleList) {
        List<Vehicle> result = new ArrayList<>();
        if (vehicleList == null) {
            return result;
        }
        for (VehicleDto vehicle : vehicleList) {
            Vehicle vehicleDom = VehicleMapper.toDomain(vehicle);
            result.add(vehicleDom);
        }
        return result;
    }
}
