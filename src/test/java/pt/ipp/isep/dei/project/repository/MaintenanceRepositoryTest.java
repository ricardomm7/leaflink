package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRepositoryTest {

    // Create a new instance of MaintenanceRepository and verify that the maintenanceList is empty
    @Test
    public void test_create_new_instance_and_verify_empty_list() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.isEmpty());
    }

    // Create a new Maintenance object and add it to the maintenanceList using addMaintenance method, then verify that the maintenanceList contains the added object
    @Test
    public void test_add_maintenance_to_list() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        Maintenance maintenance = new Maintenance("ABC123", new Date(), 10000);
        maintenanceRepository.addMaintenance(maintenance);
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.contains(maintenance));
    }

    // Create a new Maintenance object using createMaintenance method and verify that the maintenanceList contains the added objec
    @Test
    public void test_create_maintenance_using_method() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.createMaintenance("ABC123", new Date(), 10000);
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertEquals(1, maintenanceList.size());
        assertEquals("ABC123", maintenanceList.get(0).getVehiclePlate());
    }

    //create a list of Vehicle objects and call createMaintenanceReport method, then verify that the output is correct
    @Test
    public void test_create_maintenance_report() throws FileNotFoundException {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("VIN123polkiujhygt", "Brand1", "Model1", "Type1", "ABC123", 1000.0, 2000.0, 16000, new Date(), new Date(), 5000);
        Vehicle vehicle2 = new Vehicle("VIN45sxcdfvmnhre6", "Brand2", "Model2", "Type2", "DEF456", 1500.0, 2500.0, 19000, new Date(), new Date(), 6000);
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        maintenanceRepository.createMaintenance("ABC123", new Date(), 10000);
        maintenanceRepository.createMaintenance("DEF456", new Date(), 12000);
        System.out.println(maintenanceRepository.createMaintenanceReport(vehicleList));
        String expectedOutput = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "ABC123          Brand1          Model1          16000           5000            10000           21000          \n" +
                "\n" +
                "DEF456          Brand2          Model2          19000           6000            12000           25000          \n" +
                "\n";

        assertEquals(expectedOutput, maintenanceRepository.createMaintenanceReport(vehicleList));
    }

    // Create a new Maintenance object with a null date and verify that an IllegalArgumentException is thrown
    @Test
    public void test_create_maintenance_with_null_date() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("ABC123", null, 10000);
        });
    }

    // Create a new Maintenance object with a negative km value and verify that an IllegalArgumentException is thrown
    @Test
    public void test_create_maintenance_with_negative_km() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("ABC123", new Date(), -10000);
        });
    }

    // Create a new Maintenance object with an empty vehiclePlate and verify that an IllegalArgumentException is thrown
    @Test
    public void test_create_maintenance_with_empty_vehicle_plate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("", new Date(), 10000);
        });
    }

}