package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.MaintenanceReport;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.MaintenanceMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MaintenanceRepository class manages the storage and retrieval of maintenance within the application.
 */
public class MaintenanceRepository {
    private List<Maintenance> maintenanceList;
    private MaintenanceReport maintenanceReport;

    /**
     * Instantiates a new Maintenance repository.
     */
    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<Maintenance>();
        this.maintenanceReport = new MaintenanceReport();
    }

    /**
     * Create maintenance.
     *
     * @param plate     the plate
     * @param date      the date
     * @param currentKm the current km
     */
    public void createMaintenance(String plate, LocalDate date, int currentKm) {
        Maintenance maintenance = new Maintenance(plate, date, currentKm);
        addMaintenance(maintenance);
    }

    /**
     * Add maintenance.
     *
     * @param maintenance the maintenance
     */
    public void addMaintenance(Maintenance maintenance) {
        this.maintenanceList.add(maintenance);
    }

    /**
     * Gets maintenance list.
     *
     * @return the maintenance list
     */
    public List<MaintenanceDto> getMaintenanceList() {
        List<MaintenanceDto> u = new ArrayList<>();
        for (Maintenance j : maintenanceList) {
            MaintenanceDto k = MaintenanceMapper.toDto(j);
            u.add(k);
        }
        return u;
    }

    /**
     * Create maintenance report string.
     *
     * @param vehicleDtoList the vehicle dto list
     * @return the string
     */
    public String createMaintenanceReport(List<VehicleDto> vehicleDtoList) {
        return maintenanceReport.createReport(vehicleDtoList, getMaintenanceList());

    }


}

