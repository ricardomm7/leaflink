package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRepositoryTest {

    private MaintenanceRepository maintenanceRepository;

    @BeforeEach
    void setUp() {
        maintenanceRepository = new MaintenanceRepository();
    }

    // Create a new instance of MaintenanceRepository and verify that the maintenanceList is empty
    @Test
    public void test_create_new_instance_and_verify_empty_list() {
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.isEmpty());
    }

    // Create a new Maintenance object and add it to the maintenanceList using addMaintenance method, then verify that the maintenanceList contains the added object
    @Test
    public void test_add_maintenance_to_list() {
        // Arrange
        MaintenanceDto maintenanceDto = new MaintenanceDto("ABC123", LocalDate.of(2023, 2, 20), 10000);

        // Act
        maintenanceRepository.createMaintenance(maintenanceDto);
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();

        // Assert
        assertTrue(maintenanceList.contains(maintenanceDto));
    }

    // Create a new Maintenance object using createMaintenance method and verify that the maintenanceList contains the added object
    @Test
    public void test_create_maintenance_using_method() {
        MaintenanceDto maintenanceDto = new MaintenanceDto("ABC123", LocalDate.of(2023, 2, 2), 10000);

        maintenanceRepository.createMaintenance(maintenanceDto);
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();

        assertEquals(1, maintenanceList.size());
        assertEquals("ABC123", maintenanceList.get(0).getVehiclePlate());
    }

    // Create a new Maintenance object with a null date and verify that an IllegalArgumentException is thrown
    @Test
    public void test_create_maintenance_with_null_date() {
        assertThrows(NullPointerException.class, () -> {
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

    // Test removing a maintenance entry by vehicle plate and date
    @Test
    public void test_remove_maintenance() {
        // Arrange
        MaintenanceDto maintenanceDto1 = new MaintenanceDto("ABC123", LocalDate.of(2023, 2, 2), 10000);
        MaintenanceDto maintenanceDto2 = new MaintenanceDto("DEF456", LocalDate.of(2023, 3, 3), 15000);

        maintenanceRepository.createMaintenance(maintenanceDto1);
        maintenanceRepository.createMaintenance(maintenanceDto2);

        // Act
        maintenanceRepository.removeMaintenance("ABC123", LocalDate.of(2023, 2, 2));
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();

        // Assert
        assertEquals(1, maintenanceList.size());
        assertFalse(maintenanceList.contains(maintenanceDto1));
        assertTrue(maintenanceList.contains(maintenanceDto2));
    }

    // Test removing a non-existing maintenance entry should not change the list
    @Test
    public void test_remove_non_existing_maintenance() {
        // Arrange
        MaintenanceDto maintenanceDto = new MaintenanceDto("ABC123", LocalDate.of(2023, 2, 2), 10000);
        maintenanceRepository.createMaintenance(maintenanceDto);

        // Act
        maintenanceRepository.removeMaintenance("NONEXISTENT", LocalDate.of(2023, 1, 1));
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();

        // Assert
        assertEquals(1, maintenanceList.size());
        assertTrue(maintenanceList.contains(maintenanceDto));
    }
}
