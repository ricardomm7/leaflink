package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

public class MaintenanceMapper {

    public static MaintenanceDto toDto(Maintenance m) {
        return new MaintenanceDto(m.getVehiclePlate(), m.getDate(), m.getKm());
    }

    public static Maintenance toDomain(MaintenanceDto m) {
        return new Maintenance(m.getVehiclePlate(), m.getDate(), m.getKm());
    }
}
