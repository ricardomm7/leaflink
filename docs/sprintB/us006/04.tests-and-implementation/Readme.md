# US006 - Register a Vehicle 

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



_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class RegisterVehicleUI 

```java
public class RegisterVehicleUI implements Runnable {
    public static final Scanner scanner = new Scanner(System.in);


    public void registerVehicle() {
        RegisterVehicleController controller = new RegisterVehicleController();
        boolean flag = false;

        do {
            try {
                System.out.println("Enter VIN:");
                String vin = scanner.nextLine();
                validateVin(vin);

                System.out.println("Enter Brand name:");
                String brand = scanner.nextLine();
                validateBrand(brand);

                System.out.println("Enter Model name:");
                String model = scanner.nextLine();
                validateModel(model);

                System.out.println("Enter vehicle type:");
                String type = scanner.nextLine();
                validateType(type);

                System.out.println("Enter vehicle plate:");
                String vehiclePlate = scanner.nextLine();
                validateVehiclePlate(vehiclePlate);

                System.out.println("Enter tare weight:");
                double tareWeight = Double.parseDouble(scanner.nextLine());
                validateTareWeight(tareWeight);

                System.out.println("Enter gross weight:");
                double grossWeight = Double.parseDouble(scanner.nextLine());
                validateGrossWeight(grossWeight);

                System.out.println("Enter current km:");
                int currentKm = Integer.parseInt(scanner.nextLine());
                validateCurrentKm(currentKm);

                System.out.println("Enter registration date (DD/MM/YYYY):");
                String registrationDateStr = scanner.nextLine();
                Date registrationDate = parseDate(registrationDateStr);

                System.out.println("Enter acquisition date (DD/MM/YYYY):");
                String acquisitionDateStr = scanner.nextLine();
                Date acquisitionDate = parseDate(acquisitionDateStr);
                validateAcquisitionDate(registrationDate, acquisitionDate);

                System.out.println("Enter maintenance frequency (in km):");
                int maintenanceFrequency = Integer.parseInt(scanner.nextLine());
                validateMaintenanceFrequency(maintenanceFrequency);

                // Confirm Vehicle data
                System.out.println("\nConfirms Vehicle data:\n");
                System.out.println("Vin: " + vin + "\nBrand: " + brand + "\nModel: " + model + "\nVehicle Plate: " + vehiclePlate + "\nTare Weight: "
                        + tareWeight + "\nGross Weight: " + grossWeight + "\nCurrent Kilometers: " + currentKm + "\nRegistration date: " + registrationDateStr + "\nAcquisition date: "
                        + acquisitionDateStr + "\nMaintenance Frequency: " + maintenanceFrequency);

                System.out.println("\nTypes 'Yes' to confirm data:");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    if (controller.registerVehicle(vin, brand, model, type, vehiclePlate, tareWeight, grossWeight,
                            currentKm, registrationDate, acquisitionDate, maintenanceFrequency)) {
                        System.out.println("Vehicle registered successfully!");
                        flag = true;
                    } else {
                        System.out.println("Failed to register vehicle.");
                    }
                } else {
                    System.out.println("Vehicle registration canceled.");
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for maintenance frequency: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Failed to parse date: " + e.getMessage());
            } catch (InvalidDateException e) {
                System.out.println("Invalid date: " + e.getMessage());
            }
        } while (!flag);
    }

}
```

### Class RegisterVehicleController

```java
public class RegisterVehicleController {
    private final VehicleRepository vehicleRepository;
    private final Repositories repositories;

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

}
```

### VehicleRepository

````java

public class VehicleRepository {
    private final List<Vehicle> vehicleList;


    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

 
    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
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
    
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String vin) {
        if (vin == null || vin.length() != 17 || !vin.matches("[a-zA-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN must have 17 alphanumeric characters (letters and numbers).");
        }
        this.VIN = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || !brand.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Brand name must have only alphanumeric characters (letters and numbers).");
        }
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || !model.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Model name must have only alphanumeric characters (letters and numbers).");
        }
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || !type.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle type must have only alphanumeric characters (letters and numbers).");
        }
        this.type = type;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }
    
    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || !vehiclePlate.matches("[a-zA-Z0-9]{6}")) {
            throw new IllegalArgumentException("Vehicle plate must have 6 alphanumeric characters (letters and numbers).\n");
        }
        this.vehiclePlate = vehiclePlate;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double tareWeight) {
        if (tareWeight <= 0) {
            throw new IllegalArgumentException("Tare weight must be greater than zero.");
        }
        this.tareWeight = tareWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be greater than 0 Kg.");
        }
        this.grossWeight = grossWeight;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        if (currentKm < 0) {
            throw new IllegalArgumentException("Current kilometers must be greater than 0 Km.");
        }
        this.currentKm = currentKm;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenanceFrequency) {
        if (maintenanceFrequency <= 0) {
            throw new IllegalArgumentException("Maintenance frequency must be greater than 0 Km.");
        }
        this.maintenanceFrequency = maintenanceFrequency;
    }
    
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

* A new option on the Vehicle and Equipment Resources Manager menu options was added.

* For demo purposes some vehicles are bootstrapped while system starts.


## 7. Observations

n/a