package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.repository.MaintenanceRepository;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaintenanceReportTest {
    //create a list of Vehicle objects and call createMaintenanceReport method, then verify that the output is correct
    @Test
    public void test_create_maintenance_report() throws FileNotFoundException {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        MaintenanceReport maintenanceReport = new MaintenanceReport();
        List<VehicleDto> vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("VIN123polkiujhygt", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2022, 10, 12), "AB01MN", 1000.0, 2000.0, 16000, LocalDate.of(2020, 10, 2), 5000);
        Vehicle vehicle2 = new Vehicle("VIN45sxcdfvmnhre6", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2022, 10, 12), "DE24RZ", 1500.0, 2500.0, 19000, LocalDate.of(2020, 10, 10), 6000);
        vehicleList.add(VehicleMapper.toDto(vehicle1));
        vehicleList.add(VehicleMapper.toDto(vehicle2));
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2023, 2, 2), 10000);
        maintenanceRepository.createMaintenance("DE24RZ", LocalDate.of(2023, 2, 2), 12000);
        String expectedOutput = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "AB01MN          Brand1          Model1          16000           5000            10000           16000          \n" +
                "DE24RZ          Brand2          Model2          19000           6000            12000           19000          \n";

        assertEquals(expectedOutput, maintenanceReport.createReport(vehicleList, maintenanceRepository.getMaintenanceList()));
    }

    // Verify if only report the latest maintenance
    @Test
    public void test_create_maintenance_report_with_multiplesMaintenances() throws FileNotFoundException {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        MaintenanceReport maintenanceReport = new MaintenanceReport();
        List<VehicleDto> vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("VIN123polkiujhygt", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2022, 10, 12), "AB01MN", 1000.0, 2000.0, 16000, LocalDate.of(2020, 10, 2), 5000);
        vehicleList.add(VehicleMapper.toDto(vehicle1));
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2024, 2, 2), 10000);
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2023, 3, 2), 9000);
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2023, 7, 2), 9200);
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2023, 9, 2), 9300);
        maintenanceRepository.createMaintenance("AB01MN", LocalDate.of(2024, 1, 2), 9900);

        String expectedOutput = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "AB01MN          Brand1          Model1          16000           5000            10000           16000          \n";

        assertEquals(expectedOutput, maintenanceReport.createReport(vehicleList, maintenanceRepository.getMaintenanceList()));
    }

}