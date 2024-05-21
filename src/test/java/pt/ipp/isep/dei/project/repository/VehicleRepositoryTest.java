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
        Vehicle vehicle1 = new Vehicle("vfgtrfesgt567kjuy", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA00AA", 1000.0, 2000.0, 5000, LocalDate.of(2023, 10, 3), 10000);
        Vehicle vehicle2 = new Vehicle("VMj1234fdhbgterkm", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2020, 10, 3), "AA01AA", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000);
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);

        // Act
        List<Vehicle> result = vehicleRepository.getVehicleList();

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
        List<Vehicle> result = vehicleRepository.getVehicleList();

        // Assert
        assertNotSame(vehicleRepository.getVehicleList(), result);
    }

    // Does not modify the original list of vehicles in the repository
    @Test
    public void test_does_not_modify_original_list() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle1 = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2010, 10, 10), "12EW54", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000);
        vehicleRepository.addVehicle(vehicle1);

        // Act
        List<Vehicle> result = vehicleRepository.getVehicleList();
        result.add(new Vehicle("VIN1234fdhbgtedxs", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2023, 10, 12), "AA23WQ", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000));

        // Assert
        assertEquals(1, vehicleRepository.getVehicleList().size());
    }

    // Add a vehicle to the repository and verify that it is added correctly
    @Test
    public void test_add_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA21AA", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 1), 10000);

        // Act
        vehicleRepository.addVehicle(vehicle);
        List<Vehicle> result = vehicleRepository.getVehicleList();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(vehicle));
    }

    // add a vehicle with invalid params to the repository and verify that it is added
    @Test
    public void test_add_invalid_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("VIN1@@", "@@pp.2123", "Mod---el1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "ES70NE", -1000.0, -2000.0, 5000, LocalDate.of(2022, 10, 12), 10000);

        // Act
        try {
            vehicleRepository.addVehicle(vehicle);

            // Assert
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid vehicle.", e.getMessage());
        }
    }

    // add an existing vehicle to the repository and verify that it is added
    @Test
    public void test_add_existing_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "RM47CV", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 12), 10000);
        vehicleRepository.addVehicle(vehicle);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            vehicleRepository.addVehicle(vehicle);
        });

        assertEquals("Vehicle already exists.", exception.getMessage());
    }


    // verify if vehicle don't exists
    @Test
    public void test_vehicle_dont_exists() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VINlkhgtrewt53fca", "Brand", "Model", VehicleType.CAR, LocalDate.of(2013, 12, 12), "13TG52", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles("VIN12345678901234", "PLATE0");

        assertFalse(result);
    }

    // Verify if both VIN and vehicle plate are not null and have the correct length
    @Test
    public void test_both_vin_and_vehicle_plate_not_null_and_correct_length() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2014, 10, 10), "12RM68", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 10), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles("VIN12345678901234", "PLATE1");

        assertTrue(result);
    }

    // Verify if VIN is null
    @Test
    public void test_vin_is_null() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2000, 1, 1), "2345LL", 1000.0, 2000.0, 5000, LocalDate.of(2021, 1, 1), 10000));

        try {
            vehicleRepository.verifyExistingVehicles(null, "PLATE01");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("VIN and vehicle plate cannot be null.", e.getMessage());
        }
    }

    // Verify if vehicle plate is null
    @Test
    public void test_vehicle_plate_is_null() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2011, 10, 10), "88II99", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 10), 10000));

        try {
            vehicleRepository.verifyExistingVehicles("VIN12345678901234", null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("VIN and vehicle plate cannot be null.", e.getMessage());
        }
    }

    // Verify if vehicle plate is on an incorrect format
    @Test
    public void test_vehicle_plate_is_incorrect() {
        VehicleRepository vehicleRepository = new VehicleRepository();

        try {
            vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2011, 10, 10), "ESII99", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 10), 10000));
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid vehicle.", e.getMessage());
        }
    }

    // Verify VIN with length different than 17 and vehicle plate with length different than 6 throws IllegalArgumentException
    @Test
    public void test_invalid_vin_and_plate_length_throws_exception() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2010, 10, 10), "22WW44", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));


        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            vehicleRepository.verifyExistingVehicles("VIN123456789@012345998", "PLAsdcTE01");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            vehicleRepository.verifyExistingVehicles("VIN1234567890@1234765", "PLAdscTE012");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            vehicleRepository.verifyExistingVehicles("VIN12345678901@234sfwe5", "PLATE0wed12");
        });
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
        Boolean result = vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency)));

        // Assert
        assertTrue(result);
        assertEquals(1, vehicleRepository.getVehicleList().size());
    }


    // does not register a new vehicle with null or empty input values
    @Test
    public void test_does_not_register_new_vehicle_with_null_or_empty_input_values() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vin = null;
        String brand = "";
        String model = "Mode@l1";
        VehicleType type = VehicleType.CAR;
        String vehiclePlate = "PL00PT";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        LocalDate registrationDate = LocalDate.of(2023, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2024, 10, 10);
        int maintenanceFrequency = 10000;

        // Act
        Boolean result = vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency)));

        // Assert
        assertFalse(result);
        assertEquals(0, vehicleRepository.getVehicleList().size());
    }

    // does not register a vehicle with an acquisition date before the registration date
    @Test
    public void test_does_not_register_new_vehicle_with_incorrect_date_input_() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        String vin = "VIN12345678901234";
        String brand = "Brand1";
        String model = "Model1";
        VehicleType type = VehicleType.CAR;
        String vehiclePlate = "AA00AA";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        LocalDate registrationDate = LocalDate.of(2023, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2020, 9, 9);
        int maintenanceFrequency = 10000;

        Boolean result = vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency)));

        assertFalse(result);
        assertEquals(0, vehicleRepository.getVehicleList().size());
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
        vehicleRepository.addVehicle(VehicleMapper.toDomain(vehicle1));

        VehicleDto vehicle2 = new VehicleDto("VIN2kjuyhnbgvxdsc", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2011, 10, 10), "00AA01", 1500.0, 2500.0, 8000, LocalDate.of(2023, 10, 1), 10000);
        vehicleRepository.addVehicle(VehicleMapper.toDomain(vehicle2));

        VehicleDto vehicle3 = new VehicleDto("VIN3mnhjkloi87654", "Brand3", "Model3", VehicleType.CAR, LocalDate.of(2010, 10, 10), "00AA00", 1000.0, 2000.0, 100, LocalDate.of(2020, 10, 1), 7500);
        vehicleRepository.addVehicle(VehicleMapper.toDomain(vehicle3));

        // Adição das manutenções
        maintenanceRepository.createMaintenance("01AA00", LocalDate.of(2020, 10, 10), 2000);
        maintenanceRepository.createMaintenance("00AA01", LocalDate.of(2023, 10, 10), 10000);

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