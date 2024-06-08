package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaintenanceReportTest {

    private List<VehicleDto> vehicleDtoList;
    private List<MaintenanceDto> maintenanceDtoList;
    private MaintenanceReport maintenanceReport;

    @BeforeEach
    void setUp() {
        maintenanceReport = new MaintenanceReport();

        vehicleDtoList = new ArrayList<>();
        maintenanceDtoList = new ArrayList<>();

        VehicleDto vehicle1 = new VehicleDto(
                "1HGBH41JXMN109186", "Toyota", "Corolla", VehicleType.CAR,
                LocalDate.of(2020, 1, 15), "AA00AA", 1500.0, 2000.0, 55000,
                LocalDate.of(2020, 1, 15), 10000);

        VehicleDto vehicle2 = new VehicleDto(
                "2HGCM82633A123456", "Honda", "Civic", VehicleType.CAR,
                LocalDate.of(2021, 5, 10), "BB00BB", 1400.0, 1900.0, 45000,
                LocalDate.of(2021, 5, 10), 10000);

        vehicleDtoList.add(vehicle1);
        vehicleDtoList.add(vehicle2);

        MaintenanceDto maintenance1 = new MaintenanceDto("AA00AA", LocalDate.of(2023, 1, 15), 45000);
        MaintenanceDto maintenance2 = new MaintenanceDto("BB00BB", LocalDate.of(2023, 5, 10), 35000);
        maintenanceDtoList.add(maintenance1);
        maintenanceDtoList.add(maintenance2);
    }

    @Test
    void testCreateReport() {
        String expectedReport = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "AA00AA          Toyota          Corolla         55000           10000           45000           55000          \n" +
                "BB00BB          Honda           Civic           45000           10000           35000           45000          \n";

        String actualReport = maintenanceReport.createReport(vehicleDtoList, maintenanceDtoList);
        assertEquals(expectedReport, actualReport);
    }

    @Test
    void testCreateReportWithNoValidEntries() {
        VehicleDto vehicle1 = new VehicleDto(
                "1HGBH41JXMN109186", "Toyota", "Corolla", VehicleType.CAR,
                LocalDate.of(2020, 1, 15), "AA00AA", 1500.0, 2000.0, 40000,
                LocalDate.of(2020, 1, 15), 10000);
        VehicleDto vehicle2 = new VehicleDto(
                "2HGCM82633A123456", "Honda", "Civic", VehicleType.CAR,
                LocalDate.of(2021, 5, 10), "BB00BB", 1400.0, 1900.0, 38000,
                LocalDate.of(2021, 5, 10), 10000);

        vehicleDtoList.set(0, vehicle1);
        vehicleDtoList.set(1, vehicle2);

        String expectedReport = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n";
        String actualReport = maintenanceReport.createReport(vehicleDtoList, maintenanceDtoList);
        assertEquals(expectedReport, actualReport);
    }

    @Test
    void testCreateReportWithNoVehicles() {
        vehicleDtoList.clear(); // Remove all vehicles

        String expectedReport = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n";
        String actualReport = maintenanceReport.createReport(vehicleDtoList, maintenanceDtoList);
        assertEquals(expectedReport, actualReport);
    }

    @Test
    void testCreateReportWithNoMaintenances() {
        maintenanceDtoList.clear(); // Remove all maintenances

        String expectedReport = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n";
        String actualReport = maintenanceReport.createReport(vehicleDtoList, maintenanceDtoList);
        assertEquals(expectedReport, actualReport);
    }
}
