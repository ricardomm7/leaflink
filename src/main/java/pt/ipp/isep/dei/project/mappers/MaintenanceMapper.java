package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceMapper {

    public static MaintenanceDto toDto(Maintenance m) {
        return new MaintenanceDto(m.getVehiclePlate(), m.getDate(), m.getKm());
    }

    public static Maintenance toDomain(MaintenanceDto m) {
        return new Maintenance(m.getVehiclePlate(), m.getDate(), m.getKm());
    }

    public static List<MaintenanceDto> toDtoList(List<Maintenance> m) {
        List<MaintenanceDto> j = new ArrayList<>();
        for (Maintenance k : m) {
            j.add(MaintenanceMapper.toDto(k));
        }
        return j;
    }

    public static List<Maintenance> toDomainList(List<MaintenanceDto> m) {
        List<Maintenance> j = new ArrayList<>();
        for (MaintenanceDto k : m) {
            j.add(MaintenanceMapper.toDomain(k));
        }
        return j;
    }
}
