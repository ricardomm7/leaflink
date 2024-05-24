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
     * @param maintenanceDto the maintenance dto
     */
    public void createMaintenance(MaintenanceDto maintenanceDto) {
        Maintenance m = MaintenanceMapper.toDomain(maintenanceDto);
        addMaintenance(m);
    }

    /**
     * Add maintenance.
     *
     * @param maintenance the maintenance
     */
    private void addMaintenance(Maintenance maintenance) {
        this.maintenanceList.add(maintenance);
    }

    /**
     * Gets maintenance list.
     *
     * @return the maintenance list
     */
    public List<MaintenanceDto> getMaintenanceList() {
        return MaintenanceMapper.toDtoList(maintenanceList);
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

