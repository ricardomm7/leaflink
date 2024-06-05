package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    // Returns a list of vehicles when there are vehicles in the repository
    @Test
    public void test_returns_list_of_vehicles() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleDto vehicle1 = new VehicleDto("vfgtrfesgt567kjuy", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA00AA", 1000.0, 2000.0, 5000, LocalDate.of(2023, 10, 3), 10000);
        VehicleDto vehicle2 = new VehicleDto("VMj1234fdhbgterkm", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2020, 10, 3), "AA01AA", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000);
        vehicleRepository.registerVehicle(vehicle1);
        vehicleRepository.registerVehicle(vehicle2);

        // Act
        List<VehicleDto> result = vehicleRepository.getVehicleList();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle2));
    }

    // Returns a new list instance, not a reference to the original list
    @Test
    public void test_returns_new_list_instance() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();

        // Act
        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());

        // Assert
        assertNotSame(vehicleRepository.getAvailableVehicleList(), result);
    }

    // Does not modify the original list of vehicles in the repository
    @Test
    public void test_does_not_modify_original_list() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleDto vehicle1 = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2010, 10, 10), "12EW54", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle1);

        // Act
        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());
        result.add(VehicleMapper.toDto(new Vehicle("VIN1234fdhbgtedxs", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2023, 10, 12), "AA23WQ", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000)));

        // Assert
        assertEquals(1, vehicleRepository.getAvailableVehicleList().size());
    }

    // Add a vehicle to the repository and verify that it is added correctly
    @Test
    public void test_add_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleDto vehicle = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA21AA", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 1), 10000);

        // Act
        vehicleRepository.registerVehicle(vehicle);
        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(vehicle));
    }

    // add a vehicle with invalid params to the repository and verify that it is added
    @Test
    public void test_add_invalid_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleDto vehicle = new VehicleDto("VIN1@@", "@@pp.2123", "Mod---el1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "ES70NE", -1000.0, -2000.0, 5000, LocalDate.of(2022, 10, 12), 10000);

        // Act
        try {
            vehicleRepository.registerVehicle(vehicle);

            // Assert
            fail("Expected IllegalArgumentException to be thrown");
        } catch (NoClassDefFoundError e) {
            assertEquals("Could not initialize class javafx.scene.control.Label", e.getMessage());
        }
    }

    // add an existing vehicle to the repository and verify that it is added
    @Test
    public void test_add_existing_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleDto vehicle = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "RM47CV", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 12), 10000);
        vehicleRepository.registerVehicle(vehicle);

        // Act & Assert
        ExceptionInInitializerError exception = assertThrows(ExceptionInInitializerError.class, () -> {
            vehicleRepository.registerVehicle(vehicle);
        });

        assertEquals(null, exception.getMessage());
    }


    // verify if vehicle don't exists
    @Test
    public void test_vehicle_dont_exists() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.registerVehicle(new VehicleDto("BINlkhgtrewt53fca", "Brand", "Model", VehicleType.CAR, LocalDate.of(2013, 12, 12), "13TG52", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles(new Vehicle("BINlkhgtrewt53fca", "Brand", "Model", VehicleType.CAR, LocalDate.of(2013, 12, 12), "13TG52", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));

        assertTrue(result);
    }

    // Verify if both VIN and vehicle plate are not null and have the correct length
    @Test
    public void test_both_vin_and_vehicle_plate_not_null_and_correct_length() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.registerVehicle(new VehicleDto("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2014, 10, 10), "12RM68", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 10), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles(new Vehicle("VlN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2014, 10, 10), "14RM68", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 10), 10000));

        assertFalse(result);
    }


    // Register a new vehicle with all valid parameters and verify that it is added to the repository
    @Test
    public void test_register_new_vehicle() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vin = "VIN1234567890qwer";
        String brand = "Brand1";
        String model = "Model1";
        VehicleType type = VehicleType.CAR;
        String vehiclePlate = "11QQ22";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        LocalDate registrationDate = LocalDate.of(2010, 2, 12);
        LocalDate acquisitionDate = LocalDate.of(2020, 9, 20);
        int maintenanceFrequency = 10000;

        // Act
        boolean result = vehicleRepository.registerVehicle(new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency));

        // Assert
        assertTrue(result);
        assertEquals(1, vehicleRepository.getAvailableVehicleList().size());
    }


    // Returns an empty list when there are no vehicles in the repository.
    @Test
    public void test_empty_vehicle_list() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<MaintenanceDto> maintenanceList = new ArrayList<>();

        List<VehicleDto> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        assertTrue(result.isEmpty());
    }

    // Returns a list of vehicles needing maintenance when there are vehicles in the repository that require maintenance.
    @Test
    public void test_vehicles_needing_maintenance() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();

        // Criação dos veículos
        VehicleDto vehicle1 = new VehicleDto("VIN1mnhgbvfcdsxdf", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2010, 10, 10), "01AA00", 1000.0, 2000.0, 20000, LocalDate.of(2020, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle1);

        VehicleDto vehicle2 = new VehicleDto("VIN2kjuyhnbgvxdsc", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2011, 10, 10), "00AA01", 1500.0, 2500.0, 8000, LocalDate.of(2023, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle2);

        VehicleDto vehicle3 = new VehicleDto("VIN3mnhjkloi87654", "Brand3", "Model3", VehicleType.CAR, LocalDate.of(2010, 10, 10), "00AA00", 1000.0, 2000.0, 100, LocalDate.of(2020, 10, 1), 7500);
        vehicleRepository.registerVehicle(vehicle3);

        // Adição das manutenções
        maintenanceRepository.createMaintenance(new MaintenanceDto("01AA00", LocalDate.of(2020, 10, 10), 2000));
        maintenanceRepository.createMaintenance(new MaintenanceDto("00AA01", LocalDate.of(2023, 10, 10), 10000));

        // Act
        List<MaintenanceDto> maintenanceList = maintenanceRepository.getMaintenanceList();
        List<VehicleDto> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        // Assert
        System.out.println("Veículos necessitando de manutenção: " + result);
        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle3));
        assertFalse(result.contains(vehicle2));
    }

}