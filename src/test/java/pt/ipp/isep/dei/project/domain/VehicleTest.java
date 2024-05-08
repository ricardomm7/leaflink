package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class VehicleTest {

    // Creating a new Vehicle object with valid parameters should succeed
    @Test
    public void test_createVehicleWithValidParameters() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "ABC123", 1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertNotNull(vehicle);
    }


    // Create a new Vehicle object with invalid brand, model or type, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidBrandModelType() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand!", "Model", "Type", "ABC123", 1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Create a new Vehicle object with invalid VIN, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidVIN() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("VIN1234567890123!", "Brand", "Model", "Type", "ABC123", 1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Calling validateVehicle() method with invalid parameters should return false
    @Test
    public void test_validateVehicleWithInvalidParameters() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("VIN1234567890123!4", "Brand", "Model", "Type", "Ade2@BC123", -1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }

    // Create a new Vehicle object with acquisitionDate before registrationDate, call validateVehicle() method, should return false
    @Test
    public void test_validateVehicleWithInvalidDates() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date(registrationDate.getTime() - 1000);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "ABC123", 1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertFalse(vehicle.validateVehicle());
    }


    // Create a new Vehicle object with valid parameters, call getters to retrieve attributes, should return expected values
    @Test
    public void test_getters() {
        Date registrationDate = new Date();
        Date acquisitionDate = new Date();
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "ABC123", 1000.0, 2000.0, 10000, registrationDate, acquisitionDate, 5000);
        assertEquals("VIN12345678901234", vehicle.getVIN());
        assertEquals("Brand", vehicle.getBrand());
        assertEquals("Model", vehicle.getModel());
        assertEquals("Type", vehicle.getType());
        assertEquals("ABC123", vehicle.getVehiclePlate());
        assertEquals(1000.0, vehicle.getTareWeight(), 0.001);
        assertEquals(2000.0, vehicle.getGrossWeight(), 0.001);
        assertEquals(10000, vehicle.getCurrentKm());
        assertEquals(registrationDate, vehicle.getRegistrationDate());
        assertEquals(acquisitionDate, vehicle.getAcquisitionDate());
        assertEquals(5000, vehicle.getMaintenanceFrequency());
    }
}
