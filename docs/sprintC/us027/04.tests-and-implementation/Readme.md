# US027 - List all green spaces managed by a GSM.

## 4. Tests 

**Test 1:** Check if it is possible to register a vehicle with invalid parameters. **AC1**

            @Test
            public void test_does_not_register_new_vehicle_with_null_or_empty_input_values() {

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

                Boolean result = vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                        currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

                assertFalse(result);
                assertEquals(0, vehicleRepository.getVehicleList().size());
            }

**Test 2:** Check that if it is possible to add a vehicle that already exists in the repository. **AC2**

        @Test
        public void test_add_existing_vehicle_to_repository() {

            VehicleRepository vehicleRepository = new VehicleRepository();
            Vehicle vehicle = new Vehicle("VIN1234fdhbgterkm", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 5000, new Date(2020, 10, 12), new Date (2022, 10, 12), 10000);
            vehicleRepository.addVehicle(vehicle);

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                vehicleRepository.addVehicle(vehicle);
            });

            assertEquals("Vehicle already exists.", exception.getMessage());
        }

**Test 3:** Check if there is a vehicle that already exists in the repository by VIN and if it is in correct form. **AC2**

            @Test
            public void test_both_vin_and_vehicle_plate_not_null_and_correct_length() {
                VehicleRepository vehicleRepository = new VehicleRepository();
                vehicleRepository.addVehicle(new Vehicle("VIN12345678901234", "Brand", "Model", "Type", "PLATE1", 1000.0, 2000.0, 
                5000, new Date(2014,10,10), new Date(2022,10,10), 10000));

                boolean result = vehicleRepository.verifyExistingVehicles("VIN12345678901234", "PLATE1");

                assertTrue(result);
            }

**Test 4:** Check if it is possible to register a vehicle with a registration date after to acquisition date. **AC3**

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
                Date registrationDate = new Date(2023,10,10);
                Date acquisitionDate = new Date(2020,9,9);
                int maintenanceFrequency = 10000;

                Boolean result = vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                        currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

                assertFalse(result);
                assertEquals(0, vehicleRepository.getVehicleList().size());
            }


## 5. Construction (Implementation)

### Class RegisterVehicleController

```java

    public RegisterVehicleController() {
        repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }
    
    public boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, int currentKm,Date registrationDate, Date acquisitionDate,
                                   int maintenanceFrequency) {

        if (!vehicleRepository.verifyExistingVehicles(vin,vehiclePlate)) {

            return vehicleRepository.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                    currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        }

        return false;
    }

```

### VehicleRepository

````java

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        if (!vehicle.validateVehicle()) {
            throw new IllegalArgumentException("Invalid vehicle.");
        }
        if (getVehicleList().contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle already exists.");
        }
        vehicleList.add(vehicle);
    }

    public boolean verifyExistingVehicles(String vin, String vehiclePlate) {
        // Verify if params are null
        if (vin == null || vehiclePlate == null) {
            throw new IllegalArgumentException("VIN and vehicle plate cannot be null.");
        }
        // Verify the size of the params
        if (vin.length() != 17 || vehiclePlate.length() != 6) {
            throw new IllegalArgumentException("VIN must be exactly 17 characters and vehicle plate must be exactly 6 characters.");
        }

        // Verify existing vehicle
        for (Vehicle vehicle : getVehicleList()) {
            if (vin.equalsIgnoreCase(vehicle.getVIN()) || vehiclePlate.equalsIgnoreCase(vehicle.getVehiclePlate())) {
                return true;
            }
        }
        return false;
    }

    public Boolean registerVehicle(String vin, String brand, String model, String type, String vehiclePlate, double tareWeight,
                                   double grossWeight, int currentKm, Date registrationDate, Date acquisitionDate,
                                   int maintenanceFrequency) {

        Vehicle vehicle = new Vehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                currentKm, registrationDate, acquisitionDate, maintenanceFrequency);

        if (vehicle.validateVehicle()) {
            addVehicle(vehicle);
            return true;
        }
        return false;
    }

````

### Vehicle

````java

public class Vehicle {
    private String VIN;
    private String brand;
    private String model;
    private String type;
    private String vehiclePlate;
    private double tareWeight;
    private double grossWeight;
    private int currentKm;
    private Date registrationDate;
    private Date acquisitionDate;
    private int maintenanceFrequency;
    
   
    public Vehicle(String VIN, String brand, String model, String type, String vehiclePlate, double tareWeight, double grossWeight,
                   int currentKm, Date registrationDate, Date acquisitionDate, int maintenanceFrequency) {

        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.vehiclePlate = vehiclePlate;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registrationDate = registrationDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;
        
    }
    
    public boolean validateVehicle() {
        if (!brand.matches("[a-zA-Z0-9]+") || !model.matches("[a-zA-Z0-9]+") || !type.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (VIN == null || !VIN.matches("[a-zA-Z0-9]{17}+")) {
            return false;
        }

        if (((vehiclePlate == null) || !vehiclePlate.matches("[a-zA-Z0-9]{6}+"))) {
            return false;
        }

        if (tareWeight <= 0 || grossWeight <= 0 || currentKm < 0 || maintenanceFrequency <= 0) {
            return false;
        }
        if (!registrationDate.before(acquisitionDate)) {
            return false;

        }
        return true;
    }
}
````


## 6. Integration and Demo 

* A new option on the VFM menu options was added.

* For demo purposes some vehicles are bootstrapped while system starts.


## 7. Observations

n/a