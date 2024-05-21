package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

public class VehicleMapper {
    /**
     * The Vehicle mapper.
     */
    static VehicleMapper skillMapper= new VehicleMapper();

    /**
     * To dto Vehicle dto.
     *
     * @param vehicle the skill
     * @return the vehicle dto
     */
    public static VehicleDto toDto (Vehicle vehicle){
      VehicleDto vDto = new VehicleDto(vehicle.getVIN(), vehicle.getBrand(), vehicle.getModel(), vehicle.getType(),
              vehicle.getRegistrationDate(), vehicle.getVehiclePlate(), vehicle.getTareWeight(), vehicle.getGrossWeight(),
              vehicle.getCurrentKm(), vehicle.getAcquisitionDate(), vehicle.getMaintenanceFrequency());
      return vDto;
    }

    /**
     * To domain Vehicle.
     *
     * @param vehicleDto the vehicle dtp
     * @return the vehicle domain
     */
    public static Vehicle toDomain (VehicleDto vehicleDto){
      Vehicle v = new Vehicle(vehicleDto.getVIN(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getType(),
              vehicleDto.getRegistrationDate(), vehicleDto.getVehiclePlate(), vehicleDto.getTareWeight(), vehicleDto.getGrossWeight(),
              vehicleDto.getCurrentKm(), vehicleDto.getAcquisitionDate(), vehicleDto.getMaintenanceFrequency());
      return v;
    }
}
