package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.MaintenanceReport;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.MaintenanceMapper;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MaintenanceRepository class is responsible for managing the collection of Maintenance objects
 * and generating maintenance reports.
 */
public class MaintenanceRepository implements Serializable {

    private final List<Maintenance> maintenanceList;
    private final MaintenanceReport maintenanceReport;

    /**
     * Constructs a new MaintenanceRepository object and initializes the maintenance list and maintenance report.
     */
    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
        this.maintenanceReport = new MaintenanceReport();
    }

    /**
     * Creates a new Maintenance based on the provided MaintenanceDto.
     *
     * @param maintenanceDto the MaintenanceDto containing the maintenance data
     */
    public void createMaintenance(MaintenanceDto maintenanceDto) {
        Maintenance m = MaintenanceMapper.toDomain(maintenanceDto);
        try {
            addMaintenance(m);
        }catch (Exception e) {
            ShowError.showAlert("Maintenance", e.getMessage(), "Duplicate");
        }
    }

    /**
     * Adds a Maintenance to the repository.
     *
     * @param maintenance the Maintenance object to be added
     */
    private void addMaintenance(Maintenance maintenance) {
        for (Maintenance m : maintenanceList) {
            if (m.getVehiclePlate().equals(maintenance.getVehiclePlate()) &&
                    m.getDate().equals(maintenance.getDate()) && (m.getKm() == (maintenance.getKm()))) {
                throw new IllegalArgumentException("There is already a maintenance with the same attributes.");
            }else {
                maintenanceList.add(maintenance);
            }
        }
    }

    /**
     * Gets the list of MaintenanceDto objects.
     *
     * @return the list of MaintenanceDto objects
     */
    public List<MaintenanceDto> getMaintenanceList() {
        return MaintenanceMapper.toDtoList(maintenanceList);
    }

    /**
     * Creates a maintenance report based on the provided list of VehicleDto objects and the maintenance list.
     *
     * @param vehicleDtoList the list of VehicleDto objects
     * @return the maintenance report as a string
     */
    public String createMaintenanceReport(List<VehicleDto> vehicleDtoList) {
        return maintenanceReport.createReport(vehicleDtoList, getMaintenanceList());
    }

    public void removeMaintenance(String plate, LocalDate date) {
        for (Maintenance m : maintenanceList) {
            if (m.getVehiclePlate().equals(plate) && m.getDate().equals(date)) {
                maintenanceList.remove(m);
                return;
            }
        }
    }

}
