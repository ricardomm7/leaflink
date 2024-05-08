package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Maintenance;
import pt.ipp.isep.dei.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    // Returns a list of vehicles when there are vehicles in the repository
    @Test
    public void test_returns_list_of_vehicles() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle1 = new Vehicle("vfgtrfesgt567kjuy", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 5000, new Date(2020, 10, 12), new Date(2023, 10, 3), 10000);
        Vehicle vehicle2 = new Vehicle("VMj1234fdhbgterkm", "Brand2", "Model2", "Type2", "Plate2", 1500.0, 2500.0, 7000, new Date(2020, 10, 3), new Date(2024, 10, 12), 12000);
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
        Vehicle vehicle1 = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 5000, new Date(2010, 10, 10), new Date(2020, 10, 1), 10000);
        vehicleRepository.addVehicle(vehicle1);

        // Act
        List<Vehicle> result = vehicleRepository.getVehicleList();
        result.add(new Vehicle("VIN1234fdhbgtedxs", "Brand2", "Model2", "Type2", "Plate2", 1500.0, 2500.0, 7000, new Date(2023, 10, 12), new Date(2024, 1, 12), 12000));

        // Assert
        assertEquals(1, vehicleRepository.getVehicleList().size());
    }

    // Add a vehicle to the repository and verify that it is added correctly
    @Test
    public void test_add_vehicle_to_repository() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 5000, new Date(2020, 10, 12), new Date(2022, 10, 1), 10000);

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
        Vehicle vehicle = new Vehicle("VIN1@@", "@@pp.2123", "Mod---el1", "Type1", "Plate1", -1000.0, -2000.0, 5000, new Date(2020, 10, 12), new Date(2022, 10, 12), 10000);

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
        Vehicle vehicle = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 5000, new Date(2020, 10, 12), new Date(2022, 10, 12), 10000);
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
        vehicleRepository.addVehicle(new Vehicle("VINlkhgtrewt53fca", "Brand", "Model", "Type", "PLATE1", 1000.0, 2000.0, 5000, new Date(2013, 12, 12), new Date(2020, 10, 1), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles("VIN12345678901234", "PLATE0");

        assertFalse(result);
    }

    // Verify if both VIN and vehicle plate are not null and have the correct length
    @Test
    public void test_both_vin_and_vehicle_plate_not_null_and_correct_length() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "PLATE1", 1000.0, 2000.0, 5000, new Date(2014, 10, 10), new Date(2022, 10, 10), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles("VIN12345678901234", "PLATE1");

        assertTrue(result);
    }

    // Verify if VIN is null
    @Test
    public void test_vin_is_null() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "PLATE1", 1000.0, 2000.0, 5000, new Date(2000, 1, 1), new Date(2021, 1, 1), 10000));

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
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "PLATE2", 1000.0, 2000.0, 5000, new Date(2011, 10, 10), new Date(2020, 10, 10), 10000));

        try {
            vehicleRepository.verifyExistingVehicles("VIN12345678901234", null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("VIN and vehicle plate cannot be null.", e.getMessage());
        }
    }

    // Verify VIN with length different than 17 and vehicle plate with length different than 6 throws IllegalArgumentException
    @Test
    public void test_invalid_vin_and_plate_length_throws_exception() {
        // Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "PLATE1", 1000.0, 2000.0, 5000, new Date(2010, 10, 10), new Date(2020, 10, 1), 10000));


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
        String type = "Type1";
        String vehiclePlate = "Plate1";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        Date registrationDate = new Date(2010, 2, 12);
        Date acquisitionDate = new Date(2020, 9, 2013);
        int maintenanceFrequency = 10000;

        // Act
        Boolean result = vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

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
        String type = "Type1";
        String vehiclePlate = "Plate1";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        int maintenanceFrequency = 10000;

        // Act
        Boolean result = vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

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
        String type = "Type1";
        String vehiclePlate = "Plate1";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        Date registrationDate = new Date(2023, 10, 10);
        Date acquisitionDate = new Date(2020, 9, 9);
        int maintenanceFrequency = 10000;

        Boolean result = vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        assertFalse(result);
        assertEquals(0, vehicleRepository.getVehicleList().size());
    }


    // Returns an empty list when there are no vehicles in the repository.
    @Test
    public void test_empty_vehicle_list() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<Maintenance> maintenanceList = new ArrayList<>();

        List<Vehicle> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        assertTrue(result.isEmpty());
    }

    // Returns a list of vehicles needing maintenance when there are vehicles in the repository that require maintenance.
    @Test
    public void test_vehicles_needing_maintenance() {
        // Create a vehicle repository
        VehicleRepository vehicleRepository = new VehicleRepository();
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();

        // Create a list of maintenance objects
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();

        // Create a vehicle that needs maintenance
        Vehicle vehicle1 = new Vehicle("VIN1mnhgbvfcdsxdf", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 20000, new Date(2010, 10, 10), new Date(2020, 10, 1), 10000);
        vehicleRepository.addVehicle(vehicle1);
        maintenanceRepository.createMaintenance("Plate1", new Date(), 2000);


        // Create a vehicle that does not need maintenance
        Vehicle vehicle2 = new Vehicle("VIN2kjuyhnbgvxdsc", "Brand2", "Model2", "Type2", "Plate2", 1500.0, 2500.0, 8000, new Date(2011, 10, 10), new Date(2023, 10, 1), 10000);
        vehicleRepository.addVehicle(vehicle2);
        maintenanceRepository.createMaintenance("Plate2", new Date(), 10000);

        // Create a vehicle that hasn't done a maintenance
        Vehicle vehicle3 = new Vehicle("VIN3mnhjkloi87654", "Brand3", "Model3", "Type3", "Plate3", 1000.0, 2000.0, 100, new Date(2010, 10, 10), new Date(2020, 10, 1), 7500);
        vehicleRepository.addVehicle(vehicle3);
        // Call the method under test
        List<Vehicle> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        // Assert that the list contains only the vehicle that needs maintenance
        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle3));
        assertFalse(result.contains(vehicle2));
    }
}