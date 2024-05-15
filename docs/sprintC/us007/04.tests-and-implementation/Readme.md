# US007 - Register a Vehicle checkup

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

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


**Test 2:** If the getVehiclePlate method returns the value of the vehiclePlate attribute.
    
    @Test
    public void test_getVehiclePlate_returns_vehiclePlate_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2022, 1, 1), 10000);

        assert (maintenance.getVehiclePlate().equals("ABC123"));
    }

**Test 3:** If the constructor throws an exception if the vehiclesPlate parameter is null and invalid.
    
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_null() {
        new Maintenance(null, new Date(2021, 1, 1), 10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_invalid_string() {
        new Maintenance("@saswqd3432qqd", new Date(2021, 1, 1), 10000);
    }

**Test 4:** If the getKm method returns the value of the km attribute.

    @Test
    public void test_getKm_returns_km_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2021, 1, 1), 10000);
        assertEquals(10000, maintenance.getKm());

**Test 5:** If the constructor throws an exception if the kilometre parameter is negative.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_kilometragem_parameter_is_negative() {
        new Maintenance("ABC123", new Date(2021, 1, 1), -10000);
    }

**Test 6:** If the getDate method returns the value of the date attribute.

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

**Test 7:** The constructor throws an exception if the date parameter is null.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_null() {
        new Maintenance("ABC123", null, 10000);
    }

**Test 8:** The constructor throws an exception if the date parameter is an empty string.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_empty_string() {
        new Maintenance("ABC123", null, 10000);
    }


## 5. Construction (Implementation)

### Class RegisterMaintenanceController

```java
public void createMaintenance(String plate, Date maintenanceDate, int kilometers) {
    maintenanceRepository.createMaintenance(plate, maintenanceDate, kilometers);
}
```

### Class MaintenanceRepository

```java
public void createMaintenance(String plate, Date date, int currentKm) {
    Maintenance maintenance = new Maintenance(plate, date, currentKm);
    addMaintenance(maintenance);
}
```


## 6. Integration and Demo 

* A new option on the VFM menu and Admin menu was added.

## 7. Observations

n/a