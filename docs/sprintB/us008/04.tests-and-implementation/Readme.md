# US008 - List Vehicles Needing Check-up

## 4. Tests

**Test 1:** The getVehiclePlate method returns the value of the vehiclePlate attribute.

        @Test
        public void test_getVehiclePlate_returns_vehiclePlate_attribute_value() {
            Maintenance maintenance = new Maintenance("ABC123", new Date(2022,1,1),10000);

            assert(maintenance.getVehiclePlate().equals("ABC123"));
        }

**Test 2:** The constructor throws an exception if the vehiclesPlate parameter is null.

        @Test(expected = IllegalArgumentException.class)
        public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_null() {
            new Maintenance(null, new Date(2021,1,1),  10000);
        }

**Test 3:** The constructor throws an exception if the vehiclesPlate parameter is invalid.

        @Test(expected = IllegalArgumentException.class)
        public void test_constructor_throws_exception_if_vehiclesPlate_parameter_is_invalid_string() {
            new Maintenance("@saswqd3432qqd", new Date(2021,1,1), 10000);
        }

**Test 4:** Returns an empty list when there are no vehicles in the repository.

    @Test
    public void test_empty_vehicle_list() {
    VehicleRepository vehicleRepository = new VehicleRepository();
    List<Maintenance> maintenanceList = new ArrayList<>();

        List<Vehicle> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        assertTrue(result.isEmpty());
    }

** Test 5:** Returns a list of vehicles needing maintenance when there are vehicles in the repository that require
maintenance.

    @Test
    public void test_vehicles_needing_maintenance() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();

        List<Maintenance> maintenanceList = maintenanceRepository.getMaintenanceList();

        Vehicle vehicle1 = new Vehicle("VIN1", "Brand1", "Model1", "Type1", "Plate1", 1000.0, 2000.0, 20000, new Date(), new Date(), 10000);
        vehicleRepository.addVehicle(vehicle1);
        maintenanceRepository.createMaintenance("Plate1",new Date(),2000);

        Vehicle vehicle2 = new Vehicle("VIN2", "Brand2", "Model2", "Type2", "Plate2", 1500.0, 2500.0, 8000, new Date(), new Date(), 10000);
        vehicleRepository.addVehicle(vehicle2);
        maintenanceRepository.createMaintenance("Plate2",new Date(),10000);

        Vehicle vehicle3 = new Vehicle("VIN3", "Brand3", "Model3", "Type3", "Plate3", 1000.0, 2000.0,100,new Date(), new Date(), 7500);
        vehicleRepository.addVehicle(vehicle3);

        List<Vehicle> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle3));
        assertFalse(result.contains(vehicle2));
    }

**Test 2:** Create a list of Vehicle objects and call createMaintenanceReport method, then verify that the output is
correct

	@Test
    public void test_create_maintenance_report() throws FileNotFoundException {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("VIN123polkiujhygt", "Brand1", "Model1", "Type1", "ABC123", 1000.0, 2000.0, 5000, new Date(), new Date(), 5000);
        Vehicle vehicle2 = new Vehicle("VIN45sxcdfvmnhre6", "Brand2", "Model2", "Type2", "DEF456", 1500.0, 2500.0, 8000, new Date(), new Date(), 6000);
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        maintenanceRepository.createMaintenance("ABC123", new Date(), 10000);
        maintenanceRepository.createMaintenance("DEF456", new Date(), 12000);
        System.out.println(maintenanceRepository.createMaintenanceReport(vehicleList));
        String expectedOutput = "Maintenance Report\n" +
                "Plate           Brand           Model           Curr.Kms        Freq            Last            Next           \n" +
                "ABC123          Brand1          Model1          5000            5000            10000           15000          \n" +
                "\n" +
                "DEF456          Brand2          Model2          8000            6000            12000           18000          \n" +
                "\n";

        assertEquals(expectedOutput, maintenanceRepository.createMaintenanceReport(vehicleList));
    }

_It is also recommended to organize this content by subsections._

## 5. Construction (Implementation)

### CLass ListMaintenanceUI

```java
public class ListMaintenanceUI implements Runnable {
    private final ListMaintenanceController controller;

    public ListMaintenanceUI() {
        this.controller = new ListMaintenanceController();
    }

    public void listVehiclesNeedingMaintenance() {
        System.out.println("Listing vehicles needing maintenance...");

        List<Vehicle> vehiclesList = controller.getVehiclesNeedingMaintenanceList();

        if (vehiclesList.isEmpty()) {
            System.out.println("No vehicles needing maintenance.");
        } else {
            if (controller.createMaintenanceReport() != null) {
                System.out.println("Vehicles needing maintenance:");
                System.out.println(controller.createMaintenanceReport());

            }
            System.out.println("Something went wrong");
        }
    }

    @Override
    public void run() {
        listVehiclesNeedingMaintenance();

    }
}

````

### Class ListMaintenanceController

```java
public class ListMaintenanceController {
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;


    public ListMaintenanceController() {
        Repositories repositories = Repositories.getInstance();
        this.vehicleRepository = repositories.getVehicleRepository();
        this.maintenanceRepository = repositories.getMaintenanceRepository();
    }


    public List<Vehicle> getVehiclesNeedingMaintenanceList() {
        return vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());
    }


    public String createMaintenanceReport() {
        return maintenanceRepository.createMaintenanceReport(getVehiclesNeedingMaintenanceList());
    }
}
```

### Class MaintenanceRepository

```java
public class MaintenanceRepository {
    private final List<Maintenance> maintenanceList;

    public MaintenanceRepository() {
        this.maintenanceList = new ArrayList<>();
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }


    public String createMaintenanceReport(List<Vehicle> vehicleList) {
        List<Maintenance> maintenanceList = getMaintenanceList();
        StringBuilder reportBuilder = new StringBuilder();
        boolean hasValidEntry = false;

        reportBuilder.append("Maintenance Report\n");
        reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "Plate", "Brand", "Model", "Curr.Kms", "Freq", "Last", "Next"));

        for (Maintenance maintenance : maintenanceList) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle != null && maintenance.getVehiclePlate().equalsIgnoreCase(vehicle.getVehiclePlate())) {
                    hasValidEntry = true;
                    String plate = vehicle.getVehiclePlate();
                    String brand = vehicle.getBrand();
                    String model = vehicle.getModel();
                    int curr_km = vehicle.getCurrentKm();
                    int freq = vehicle.getMaintenanceFrequency();
                    int last = maintenance.getKm();
                    int next = last + freq;
                    reportBuilder.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                            plate, brand, model, curr_km, freq, last, next));
                }
            }
            reportBuilder.append("\n");
        }

        if (hasValidEntry) {
            return reportBuilder.toString();
        }
        return null;

    }

}
```

### Class VehicleRepository

````java

public class VehicleRepository {
    private final List<Vehicle> vehicleList;

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesNeedingMaintenanceList(List<Maintenance> maintenanceList) {
        List<Vehicle> vehiclesNeedingMaintenance = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            double currentKm = vehicle.getCurrentKm();
            double maintenanceFrequency = vehicle.getMaintenanceFrequency();
            double lastMaintenanceKm = getLastMaintenanceKm(vehicle.getVehiclePlate(), maintenanceList);

            if (lastMaintenanceKm == -1 || currentKm - lastMaintenanceKm >= maintenanceFrequency) {
                vehiclesNeedingMaintenance.add(vehicle);
            }
        }
        return vehiclesNeedingMaintenance;
    }

    private double getLastMaintenanceKm(String vehiclePlate, List<Maintenance> maintenanceList) {
        double lastMaintenanceKm = -1; // Valor padrão para indicar que não há registro de manutenção

        // Lógica para obter o km da última manutenção para o veículo com a placa especificada
        for (Maintenance maintenance : maintenanceList) {
            if (maintenance.getVehiclePlate().equals(vehiclePlate)) {
                lastMaintenanceKm = maintenance.getKm();
            }
        }

        return lastMaintenanceKm;
    }

}
````

````java

public class Maintenance {
    private final String vehiclePlate;
    private final Date date;
    private final int km;

    public Maintenance(String vehiclePlate, Date date, int kilometers) {
        if (kilometers < 0) {
            throw new IllegalArgumentException("Kilometers cannot be negative");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (vehiclePlate == null || vehiclePlate.isEmpty() || !vehiclePlate.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Vehicle plate cannot be null or empty");
        }
        this.vehiclePlate = vehiclePlate;
        this.date = date;
        this.km = kilometers;
    }

    public Date getDate() {
        return date;
    }

    public int getKm() {
        return km;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }
}

````

## 6. Integration and Demo

* A new option on the Vehicle and Equipment Resources Manager menu options was added.

## 7. Observations

n/a