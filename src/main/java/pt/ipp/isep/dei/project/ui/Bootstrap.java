package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.mappers.*;
import pt.ipp.isep.dei.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.time.LocalDate;

/**
 * The type Bootstrap.
 */
public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addSkills();
        addJobs();
        addVehicles();
        addCollaborators();
        addMaintenance();
        addGreenSpaces();
    }

    /**
     * Add vehicles.
     */
    public void addVehicles() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        // Example 1
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("VIN12345678901234", "Toyota", "Corolla", VehicleType.CAR, LocalDate.of(2013, 3, 15), "12AB12", 1500.0, 2000.0,
                50000, LocalDate.of(2023, 3, 12), 10000)));

        // Example 2
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("VIN56789012345678", "Ford", "Fiesta", VehicleType.CAR, LocalDate.of(2010, 10, 10), "56CD56", 1200.0, 1800.0,
                40000, LocalDate.of(2020, 9, 30), 8000)));

        // Example 3
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("AIU1qasw23edfr45t", "Volkswagen", "Golf", VehicleType.CAR, LocalDate.of(2000, 10, 22), "9012EF", 1400.0, 2100.0,
                60000, LocalDate.of(2010, 10, 1), 12000)));

        // Example 4: Utility Truck for Landscaping Equipment
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("MLN56789012345678", "Chevrolet", "Silverado", VehicleType.CAR, LocalDate.of(2019, 8, 20), "46CD56", 2800.0, 4000.0,
                90000, LocalDate.of(2024, 8, 19), 25000)));

        // Example 5: Ford F-250 for Heavy Duty Landscaping
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("HIN90123456789012", "Ford", "F-250", VehicleType.CAR, LocalDate.of(2018, 10, 10), "90EF90", 3200.0, 5000.0,
                120000, LocalDate.of(2024, 10, 9), 30000)));

        // Example 6: Isuzu NQR for Commercial Landscaping
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("KIN23456789012345", "Isuzu", "NQR", VehicleType.CAR, LocalDate.of(2016, 6, 25), "23GH12", 3500.0, 6000.0,
                150000, LocalDate.of(2024, 6, 24), 35000)));

        // Example 7: Mitsubishi Fuso Canter for Green Waste Collection
        vehicleRepository.registerVehicle(VehicleMapper.toDomain(new VehicleDto("PIN67890123456789", "Mitsubishi", "Fuso Canter", VehicleType.CAR, LocalDate.of(2017, 7, 30), "67IJ76", 4000.0, 7000.0,
                180000, LocalDate.of(2024, 7, 29), 40000)));
    }

    private void addMaintenance() {
        MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();

        // Example 1: Maintenance for Ford Transit
        maintenanceRepository.createMaintenance("12AB12", LocalDate.of(2024, 6, 10), 25000);

        // Example 2: Maintenance for Utility Truck
        maintenanceRepository.createMaintenance("56CD56", LocalDate.of(2024, 6, 15), 6500);

        // Example 3: Maintenance for Ford F-250
        maintenanceRepository.createMaintenance("9012EF", LocalDate.of(2024, 6, 20), 12500);

        // Example 4: Maintenance for Isuzu NQR
        maintenanceRepository.createMaintenance("23GH12", LocalDate.of(2024, 6, 25), 11000);

        // Example 5: Maintenance for Mitsubishi Fuso Canter
        maintenanceRepository.createMaintenance("67IJ76", LocalDate.of(2024, 7, 1), 19000);
    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        // Example 1
        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Class A Driving Licence")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Class B Driving Licence")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Class C Driving Licence")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Operation of Landscaping Machinery")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Irrigation System Installation and Maintenance")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Pruning and Trimming Techniques")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Soil Testing and Analysis")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Pest and Weed Control")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Hardscape Installation")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Tree Planting and Care")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Safety Procedures for Green Space Maintenance")));

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto("Seasonal Planting and Maintenance")));
    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Manager")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Heavy Equipment Operator")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Landscaping Technician")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Irrigation Technician")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Arborist")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Pest Control Technician")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Driver")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Gardener")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Plumber")));

        jobRepository.createJob(JobMapper.toDomain(new JobDto("Machine Operator")));
    }

    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        // Example 1
        collaboratorRepository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Bob Smith", LocalDate.of(2000, 9, 30), 987654321,
                123456789, "bob.smith@example.com", "5678 Oak Street", "6789-100",
                "Shelbyville", DocumentType.PASSPORT, "CD2345678", LocalDate.of(2021, 9, 30),
                JobMapper.toDomain(jobRepository.getJobList().get(0)))));

        // Example 2
        collaboratorRepository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Alice Johnson", LocalDate.of(2000, 9, 30), 123456789,
                987654321, "alice.johnson@example.com", "1234 Elm Street", "1234-125",
                "Springfield", DocumentType.PASSPORT, "AB1234567", LocalDate.of(2021, 9, 30),
                JobMapper.toDomain(jobRepository.getJobList().get(1)))));

        // Example 3
        collaboratorRepository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Charlie Brown", LocalDate.of(2000, 9, 30), 234567891,
                876543219, "charlie.brown@example.com", "3456 Maple Street", "2345-226",
                "Centerville", DocumentType.PASSPORT, "EF3456789", LocalDate.of(2021, 9, 30),
                JobMapper.toDomain(jobRepository.getJobList().get(2)))));

        // Example 4
        collaboratorRepository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Diana Prince", LocalDate.of(2000, 9, 30), 345678912,
                765432198, "diana.prince@example.com", "4567 Birch Street", "3456-987",
                "Metro City", DocumentType.PASSPORT, "GH4567890", LocalDate.of(2021, 9, 30),
                JobMapper.toDomain(jobRepository.getJobList().get(3)))));

        // Example 5
        collaboratorRepository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Edward Scissorhands", LocalDate.of(2000, 9, 30), 456789123,
                654321987, "edward.scissorhands@example.com", "5678 Pine Street", "4567-908",
                "Suburbia", DocumentType.PASSPORT, "IJ5678901", LocalDate.of(2021, 9, 30),
                JobMapper.toDomain(jobRepository.getJobList().get(4)))));
    }

    private void addGreenSpaces() {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        Email email = new Email("gsm@this.app");
        Password password = new Password("gsm");
        UserSession managerSession = new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(email, password, "Green Space Manager")));
        Address address = new Address("Santa Acácia", "Uriunda", "5555-999");

        greenSpaceRepository.create(GreenSpaceMapper.toDomain(new GreenSpaceDto("Alameda", GreenSpaceType.MEDIUM_SIZED_PARK, 78876, managerSession, address.getAddress(), address.getCity(), address.getZipCode())));

        Address address2 = new Address("Rua das Flores", "Florina", "1234-567");
        greenSpaceRepository.create(GreenSpaceMapper.toDomain(new GreenSpaceDto("Jardim das Flores", GreenSpaceType.GARDEN, 5000, managerSession, address2.getAddress(), address2.getCity(), address2.getZipCode())));

        Address address3 = new Address("Avenida Central", "Centro", "8901-234");
        greenSpaceRepository.create(GreenSpaceMapper.toDomain(new GreenSpaceDto("Parque Central", GreenSpaceType.LARGE_SIZED_PARK, 150000, managerSession, address3.getAddress(), address3.getCity(), address3.getZipCode())));

        Address address4 = new Address("Estrada Verde", "Verde", "5678-901");
        greenSpaceRepository.create(GreenSpaceMapper.toDomain(new GreenSpaceDto("Bosque Verde", GreenSpaceType.MEDIUM_SIZED_PARK, 250000, managerSession, address4.getAddress(), address4.getCity(), address4.getZipCode())));

        Address address5 = new Address("Praça Azul", "Azulina", "2345-678");
        greenSpaceRepository.create((GreenSpaceMapper.toDomain(new GreenSpaceDto("Praça Azul", GreenSpaceType.GARDEN, 20000, managerSession, address5.getAddress(), address5.getCity(), address5.getZipCode()))));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);


        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Human Resources Manager", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Green Space Manager", "gsm@this.app", "gsm",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("Collaborator", "collaborator@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("Software Quality Assessment Team Manager", "qam@this.app", "qam",
                AuthenticationController.ROLE_QAM);
    }
}