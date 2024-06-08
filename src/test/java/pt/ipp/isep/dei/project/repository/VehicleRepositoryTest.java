package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.MaintenanceDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        vehicleRepository = new VehicleRepository();
    }

    @Test
    public void test_returns_list_of_vehicles() {
        VehicleDto vehicle1 = new VehicleDto("vfgtrfesgt567kjuy", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA00AA", 1000.0, 2000.0, 5000, LocalDate.of(2023, 10, 3), 10000);
        VehicleDto vehicle2 = new VehicleDto("VMj1234fdhbgterkm", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2020, 10, 3), "AA01AA", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000);
        vehicleRepository.registerVehicle(vehicle1);
        vehicleRepository.registerVehicle(vehicle2);

        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getVehicleList());

        assertEquals(2, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle2));
    }

    @Test
    public void test_returns_new_list_instance() {
        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getVehicleList());

        assertNotSame(vehicleRepository.getAvailableVehicleList(), result);
    }

    @Test
    public void test_does_not_modify_original_list() {
        VehicleDto vehicle1 = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2010, 10, 10), "12EW54", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle1);

        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());
        result.add(VehicleMapper.toDto(new Vehicle("VIN1234fdhbgtedxs", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2023, 10, 12), "AA23WQ", 1500.0, 2500.0, 7000, LocalDate.of(2024, 1, 12), 12000)));

        assertEquals(1, vehicleRepository.getAvailableVehicleList().size());
    }

    @Test
    public void test_add_vehicle_to_repository() {
        VehicleDto vehicle = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "AA21AA", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 1), 10000);

        vehicleRepository.registerVehicle(vehicle);
        List<VehicleDto> result = VehicleMapper.toDtoList(vehicleRepository.getAvailableVehicleList());

        assertEquals(1, result.size());
        assertTrue(result.contains(vehicle));
    }

    @Test
    public void test_add_existing_vehicle_to_repository() {
        VehicleDto vehicle = new VehicleDto("VIN1234fdhbgterkm", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2020, 10, 12), "RM47CV", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 12), 10000);
        vehicleRepository.registerVehicle(vehicle);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            vehicleRepository.registerVehicle(vehicle);
        });

        assertEquals("Vehicle already registered", exception.getMessage());
    }

    @Test
    public void test_vehicle_dont_exists() {
        vehicleRepository.registerVehicle(new VehicleDto("BINlkhgtrewt53fca", "Brand", "Model", VehicleType.CAR, LocalDate.of(2013, 12, 12), "13TG52", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles(new Vehicle("BINlkhgtrewt53fca", "Brand", "Model", VehicleType.CAR, LocalDate.of(2013, 12, 12), "13TG52", 1000.0, 2000.0, 5000, LocalDate.of(2020, 10, 1), 10000));

        assertTrue(result);
    }

    @Test
    public void test_both_vin_and_vehicle_plate_not_null_and_correct_length() {
        vehicleRepository.registerVehicle(new VehicleDto("VIN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2014, 10, 10), "12RM68", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 10), 10000));

        boolean result = vehicleRepository.verifyExistingVehicles(new Vehicle("VlN12345678901234", "Brand", "Model", VehicleType.CAR, LocalDate.of(2014, 10, 10), "14RM68", 1000.0, 2000.0, 5000, LocalDate.of(2022, 10, 10), 10000));

        assertFalse(result);
    }

    @Test
    public void test_register_new_vehicle() {
        String vin = "VIN1234567890qwer";
        String brand = "Brand1";
        String model = "Model1";
        VehicleType type = VehicleType.CAR;
        String vehiclePlate = "11QQ22";
        double tareWeight = 1000.0;
        double grossWeight = 2000.0;
        int currentKm = 5000;
        LocalDate registrationDate = LocalDate.of(2010, 2, 12);
        LocalDate acquisitionDate = LocalDate.of(2020, 9, 20);
        int maintenanceFrequency = 10000;

        boolean result = vehicleRepository.registerVehicle(new VehicleDto(vin, brand, model, type, registrationDate, vehiclePlate, tareWeight, grossWeight,
                currentKm, acquisitionDate, maintenanceFrequency));

        assertTrue(result);
        assertEquals(1, vehicleRepository.getAvailableVehicleList().size());
    }

    @Test
    public void test_empty_vehicle_list() {
        List<MaintenanceDto> maintenanceList = new ArrayList<>();

        List<VehicleDto> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceList);

        assertTrue(result.isEmpty());
    }

    @Test
    public void test_vehicles_needing_maintenance() {
        MaintenanceRepository maintenanceRepository = new MaintenanceRepository();

        VehicleDto vehicle1 = new VehicleDto("VIN1mnhgbvfcdsxdf", "Brand1", "Model1", VehicleType.CAR, LocalDate.of(2010, 10, 10), "01AA00", 1000.0, 2000.0, 20000, LocalDate.of(2020, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle1);

        VehicleDto vehicle2 = new VehicleDto("VIN2kjuyhnbgvxdsc", "Brand2", "Model2", VehicleType.CAR, LocalDate.of(2011, 10, 10), "00AA01", 1500.0, 2500.0, 8000, LocalDate.of(2023, 10, 1), 10000);
        vehicleRepository.registerVehicle(vehicle2);

        VehicleDto vehicle3 = new VehicleDto("VIN3mnhjkloi87654", "Brand3", "Model3", VehicleType.CAR, LocalDate.of(2010, 10, 10), "00AA00", 1000.0, 2000.0, 100, LocalDate.of(2020, 10, 1), 7500);
        vehicleRepository.registerVehicle(vehicle3);

        maintenanceRepository.createMaintenance(new MaintenanceDto("VIN1mnhgbvfcdsxdf", LocalDate.of(2023, 8, 21), 12000));
        maintenanceRepository.createMaintenance(new MaintenanceDto("VIN2kjuyhnbgvxdsc", LocalDate.of(2023, 8, 21), 6000));
        maintenanceRepository.createMaintenance(new MaintenanceDto("VIN3mnhjkloi87654", LocalDate.of(2023, 8, 21), 0));

        List<VehicleDto> result = vehicleRepository.getVehiclesNeedingMaintenanceList(maintenanceRepository.getMaintenanceList());

        assertEquals(3, result.size());
        assertTrue(result.contains(vehicle1));
        assertTrue(result.contains(vehicle3));
    }
}
