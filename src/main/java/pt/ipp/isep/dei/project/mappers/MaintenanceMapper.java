package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

public class MaintenanceMapper {

    public static MaintenanceDto toDto(Maintenance m) {
        MaintenanceDto mntn = new MaintenanceDto(m.getVehiclePlate(), m.getDate(), m.getKm());
        return mntn;
    }

    public static Maintenance toDomain(MaintenanceDto m) {
        Maintenance m1 = new Maintenance(m.getVehiclePlate(), m.getDate(), m.getKm());
        return m1;
    }
}
