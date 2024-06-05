package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testCreateVehicleWithValidParameters() {
        LocalDate registrationDate = LocalDate.of(2020, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        assertNotNull(vehicle);
        assertEquals("VIN12345678901234", vehicle.getVIN());
        assertEquals("Brand", vehicle.getBrand());
        assertEquals("Model", vehicle.getModel());
        assertEquals(VehicleType.CAR, vehicle.getType());
        assertEquals(registrationDate, vehicle.getRegistrationDate());
        assertEquals("AB12CD", vehicle.getVehiclePlate());
        assertEquals(1000.0, vehicle.getTareWeight());
        assertEquals(2000.0, vehicle.getGrossWeight());
        assertEquals(10000, vehicle.getCurrentKm());
        assertEquals(acquisitionDate, vehicle.getAcquisitionDate());
        assertEquals(5000, vehicle.getMaintenanceFrequency());
    }

    @Test
    void testCreateVehicleWithInvalidVIN() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN123456789012", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000));
    }

    @Test
    void testCreateVehicleWithInvalidBrand() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand@123", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000));
    }

    @Test
    void testCreateVehicleWithInvalidModel() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model@123", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000));
    }

    @Test
    void testCreateVehicleWithInvalidRegistrationDate() {
        LocalDate acquisitionDate = LocalDate.of(2020, 9, 1);
        assertThrows(DateTimeException.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2023, 2, 30), "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000));
    }

    @Test
    void testCreateVehicleWithInvalidPlate() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "ABCDEFG", 1000.0, 2000.0, 10000, acquisitionDate, 5000));
    }


    @Test
    void testCreateVehicleWithInvalidGrossWeight() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 0.0, 10000, acquisitionDate, 5000));
    }

    @Test
    void testCreateVehicleWithInvalidMaintenanceFrequency() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 0));
    }

    @Test
    void testCreateVehicleWithTareWeightGreaterThanGrossWeight() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        assertThrows(NoClassDefFoundError.class, () -> new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 2000.0, 1000.0, 10000, acquisitionDate, 5000));
    }


    @Test
    void testEquals() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        Vehicle vehicle1 = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        Vehicle vehicle2 = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        Vehicle vehicle3 = new Vehicle("VIN87654321098765", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);

        assertTrue(vehicle1.equals(vehicle2));
        assertFalse(vehicle1.equals(vehicle3));
    }

    @Test
    void testHashCode() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2023, 9, 1);
        Vehicle vehicle1 = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        Vehicle vehicle2 = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        Vehicle vehicle3 = new Vehicle("VIN87654321098765", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);

        assertEquals(vehicle1.hashCode(), vehicle2.hashCode());
        assertNotEquals(vehicle1.hashCode(), vehicle3.hashCode());
    }


    @Test
    void testCreateVehicleWithAcquisitionDateAfterRegistrationDate() {
        LocalDate registrationDate = LocalDate.of(2021, 10, 10);
        LocalDate acquisitionDate = LocalDate.of(2022, 1, 1);
        Vehicle vehicle = new Vehicle("VIN12345678901234", "Brand", "Model", VehicleType.CAR, registrationDate, "AB12CD", 1000.0, 2000.0, 10000, acquisitionDate, 5000);
        assertNotNull(vehicle);
    }

}
