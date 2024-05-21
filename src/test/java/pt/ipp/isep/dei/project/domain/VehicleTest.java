package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class VehicleTest {

    // Creating a new Vehicle object with valid parameters should succeed
    @Test
    public void test_createVehicleWithValidParameters() {
        LocalDate registrationDate = LocalDate.of(2010,10,10);
        LocalDate acquisitionDate = LocalDate.of(2024,1,1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate,"00AA00", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        assertNotNull(vehicle);
    }


    // Create a new Vehicle object with invalid brand, model or type, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidBrandModelType() {
        LocalDate registrationDate = LocalDate.of(2021,10,10);
        LocalDate acquisitionDate = LocalDate.of(2024,1,1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand!", "Model", VehicleType.CAR, registrationDate,"AB12CD", 1000.0, 2000.0, 10000,  acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Create a new Vehicle object with invalid VIN, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidVIN() {
        LocalDate registrationDate = LocalDate.of(2021,10,10);
        LocalDate acquisitionDate = LocalDate.of(2024,1,1);
        Vehicle vehicle = new Vehicle("VIN1234567890123!", "Brand", "Model", VehicleType.UTILITY_VEHICLE, registrationDate,"CF44TG", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Calling validateVehicle() method with invalid parameters should return false
    @Test
    public void test_validateVehicleWithInvalidParameters() {
        LocalDate registrationDate = LocalDate.of(2021,10,10);
        LocalDate acquisitionDate = LocalDate.of(2024,1,1);
        Vehicle vehicle = new Vehicle("VIN1234567890123!4", "Brand", "Model", VehicleType.CAR, registrationDate,"Ade2@BC123", -1000.0, 2000.0, 10000,  acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Create a new Vehicle object with acquisitionDate before registrationDate, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidDates() {
        LocalDate registrationDate = LocalDate.of(2021,10,10);
        LocalDate acquisitionDate = LocalDate.of(2004,1,1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate,"ABC123", 1000.0, 2000.0, 10000,  acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }


    // Create a new Vehicle object with valid parameters, call getters to retrieve attributes, should return expected values
    @Test
    public void test_getters() {
        LocalDate registrationDate = LocalDate.of(2021,10,10);
        LocalDate acquisitionDate = LocalDate.of(2024,1,1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate,"AB12AA", 1000.0, 2000.0, 10000,  acquisitionDate, 5000);
        assertEquals("VIN12345678901234", vehicle.getVIN());
        assertEquals("Brand", vehicle.getBrand());
        assertEquals("Model", vehicle.getModel());
        assertEquals(VehicleType.CAR, vehicle.getType());
        assertEquals("AB12AA", vehicle.getVehiclePlate());
        assertEquals(1000.0, vehicle.getTareWeight(), 0.001);
        assertEquals(2000.0, vehicle.getGrossWeight(), 0.001);
        assertEquals(10000, vehicle.getCurrentKm());
        assertEquals(registrationDate, vehicle.getRegistrationDate());
        assertEquals(acquisitionDate, vehicle.getAcquisitionDate());
        assertEquals(5000, vehicle.getMaintenanceFrequency());
    }
}
