package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRepositoryTest {

    // Create a new instance of MaintenanceRepository and verify that the maintenanceList is empty
    @Test
    public void test_create_new_instance_and_verify_empty_list() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.isEmpty());
    }

    // Create a new Maintenance object and add it to the maintenanceList using addMaintenance method, then verify that the maintenanceList contains the added object
    @Test
    public void test_add_maintenance_to_list() {
        // Arrange
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        MaintenanceDto maintenanceDto = new MaintenanceDto("ABC123", LocalDate.of(2023, 2, 20), 10000);

        // Act
        maintenanceRepository.createMaintenance("ABC123", LocalDate.of(2023, 2, 20), 10000);
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();

        // Assert
        assertTrue(maintenanceList.contains(maintenanceDto));
    }


    // Create a new Maintenance object using createMaintenance method and verify that the maintenanceList contains the added objec
    @Test
    public void test_create_maintenance_using_method() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.createMaintenance("ABC123", LocalDate.of(2023, 2, 2), 10000);
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertEquals(1, maintenanceList.size());
        assertEquals("ABC123", maintenanceList.get(0).getVehiclePlate());
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
            new Maintenance("ABC123", LocalDate.of(2023, 10, 10), -10000);
        });
    }

    // Create a new Maintenance object with an empty vehiclePlate and verify that an IllegalArgumentException is thrown
    @Test
    public void test_create_maintenance_with_empty_vehicle_plate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("", LocalDate.of(2023, 10, 10), 10000);
        });
    }

}