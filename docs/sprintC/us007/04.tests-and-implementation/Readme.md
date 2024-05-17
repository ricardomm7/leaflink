# US007 - Register a Vehicle checkup

## 4. Tests 

**Test 1:** Verifies that the constructor of the Maintenance class correctly initializes its attributes.

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


**Test 2:** Verifies that the getVehiclePlate method of the Maintenance class correctly returns the value of the vehiclePlate attribute.

    @Test
    public void test_getVehiclePlate_returns_vehiclePlate_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2022, 1, 1), 10000);

        assert (maintenance.getVehiclePlate().equals("ABC123"));
    }

**Test 3:** Verifies that the constructor of the Maintenance class throws an IllegalArgumentException if the vehiclePlate parameter is null.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_null() {
        new Maintenance(null, new Date(2021, 1, 1), 10000);
    }

**Test 4:** Verifies that the constructor of the Maintenance class throws an IllegalArgumentException if the vehiclePlate parameter is an invalid string.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_invalid_string() {
        new Maintenance("@saswqd3432qqd", new Date(2021, 1, 1), 10000);
    }

**Test 5:** Verifies that the getKm method of the Maintenance class correctly returns the value of the km attribute.

    @Test
    public void test_getKm_returns_km_attribute_value() {
        Maintenance maintenance = new Maintenance("ABC123", new Date(2021, 1, 1), 10000);
        assertEquals(10000, maintenance.getKm());
    }

**Test 6:** Verifies that the constructor of the Maintenance class throws an IllegalArgumentException if the km (kilometragem) parameter is negative.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_kilometragem_parameter_is_negative() {
        new Maintenance("ABC123", new Date(2021, 1, 1), -10000);
    }

**Test 7:** Verifies that the getDate method of the Maintenance class correctly returns the value of the date attribute.

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

**Test 8:** Verifies that the constructor of the Maintenance class throws an IllegalArgumentException if the date parameter is null.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_null() {
        new Maintenance("ABC123", null, 10000);
    }

**Test 9:** Ensures that the constructor of the Maintenance class throws an IllegalArgumentException if the date parameter is provided as an empty string.

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_throws_exception_if_date_parameter_is_empty_string() {
        new Maintenance("ABC123", null, 10000);
    }

**Test 10:**  Verifies that when a new instance of MaintenanceRepository is created, the maintenanceList is initially empty.

    @Test
    public void test_create_new_instance_and_verify_empty_list() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.isEmpty());
    }

**Test 11:** Create a new Maintenance object and add it to the maintenanceList using addMaintenance method, then verify that the maintenanceList contains the added object

    @Test
    public void test_add_maintenance_to_list() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        Maintenance maintenance = new Maintenance("ABC123", new Date(), 10000);
        maintenanceRepository.addMaintenance(maintenance);
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertTrue(maintenanceList.contains(maintenance));
    }

**Test 12:** Create a new Maintenance object using createMaintenance method and verify that the maintenanceList contains the added objec

    @Test
    public void test_create_maintenance_using_method() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        maintenanceRepository.createMaintenance("ABC123", new Date(), 10000);
        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();
        assertEquals(1, maintenanceList.size());
        assertEquals("ABC123", maintenanceList.get(0).getVehiclePlate());
    }
**Test 13:** Create a list of Vehicle objects and call createMaintenanceReport method, then verify that the output is correct

    @Test
    public void test_create_maintenance_report() throws FileNotFoundException {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("VIN123polkiujhygt", "Brand1", "Model1", "Type1", "ABC123", 1000.0, 2000.0, 16000, new Date(), new Date(), 5000);
        Vehicle vehicle2 = new Vehicle("VIN45sxcdfvmnhre6", "Brand2", "Model2", "Type2", "DEF456", 1500.0, 2500.0, 19000, new Date(), new Date(), 6000);
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        maintenanceRepository.createMaintenance("ABC123", new Date(), 10000);
        maintenanceRepository.createMaintenance("DEF456", new Date(), 12000);
        System.out.println(maintenanceRepository.createMaintenanceReport(vehicleList));
        String expectedOutput = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "ABC123          Brand1          Model1          16000           5000            10000           21000          \n" +
                "\n" +
                "DEF456          Brand2          Model2          19000           6000            12000           25000          \n" +
                "\n";

        assertEquals(expectedOutput, maintenanceRepository.createMaintenanceReport(vehicleList));
    }

**Test 14:** Create a new Maintenance object with a null date and verify that an IllegalArgumentException is thrown

    @Test
    public void test_create_maintenance_with_null_date() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("ABC123", null, 10000);
        });
    }

**Test 15:** Create a new Maintenance object with a negative km value and verify that an IllegalArgumentException is thrown

    @Test
    public void test_create_maintenance_with_negative_km() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("ABC123", new Date(), -10000);
        });
    }

**Test 16:** Create a new Maintenance object with an empty vehiclePlate and verify that an IllegalArgumentException is thrown

    @Test
    public void test_create_maintenance_with_empty_vehicle_plate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maintenance("", new Date(), 10000);
        });
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