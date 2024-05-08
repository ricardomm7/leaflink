package pt.ipp.isep.dei.project.domain;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaintenanceTest {

    @Test
    public void test_constructor_initializes_attributes() {
        // Arrange
        Date expectedData = new Date(2023, 12, 10);
        String expectedVehiclePlate = "ABC123";
        int expectedKm = 10000;

        // Act
        Maintenance maintenance = new Maintenance(expectedVehiclePlate, expectedData, expectedKm);

        // Assert
        assertEquals(expectedData, maintenance.getDate());
        assertEquals(expectedVehiclePlate, maintenance.getVehiclePlate());
        assertEquals(expectedKm, maintenance.getKm());
    }


    // The getVehiclePlate method returns the value of the vehiclePlate attribute.
    @Test
    public void test_getVehiclePlate_returns_vehiclePlate_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2022, 1, 1), 10000);

        assert (maintenance.getVehiclePlate().equals("ABC123"));
    }

    // The constructor throws an exception if the vehiclesPlate parameter is null.
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_null() {
        new Maintenance(null, new Date(2021, 1, 1), 10000);
    }

    // The constructor throws an exception if the vehiclesPlate parameter is an invalid.
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_invalid_string() {
        new Maintenance("@saswqd3432qqd", new Date(2021, 1, 1), 10000);
    }


    // The getKm method returns the value of the km attribute.
    @Test
    public void test_getKm_returns_km_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2021, 1, 1), 10000);
        assertEquals(10000, maintenance.getKm());
    }

    // The constructor throws an exception if the kilometre parameter is negative.
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_kilometragem_parameter_is_negative() {
        new Maintenance("ABC123", new Date(2021, 1, 1), -10000);
    }

    // The getDate method returns the value of the date attribute
    @Test
    public void test_getData_returns_date_attribute_value() {
        // Arrange
        Date expectedData = new Date(2021, 1, 1);
        Maintenance maintenance = new Maintenance("ABC123", expectedData, 10000);

        // Act
        Date actualData = maintenance.getDate();

        // Assert
        assertEquals(expectedData, actualData);

    }

    // The constructor throws an exception if the date parameter is null.
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_null() {
        new Maintenance("ABC123", null, 10000);
    }

    // The constructor throws an exception if the date parameter is an empty string.
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_empty_string() {
        new Maintenance("ABC123", null, 10000);
    }

}
