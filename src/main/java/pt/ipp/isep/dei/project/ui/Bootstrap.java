package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addSkills();
        addJobs();
        addVehicles();
        addCollaborators();
        addMaintenance();


    }

public void addVehicles() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        // Example 1
        vehicleRepository.registerVehicle("VIN12345678901234", "Toyota", "Corolla", VehicleType.CAR, LocalDate.of(2013, 3, 15), "12AB12", 1500.0, 2000.0,
                50000, LocalDate.of(2023, 3, 12), 10000);

        // Example 2
        vehicleRepository.registerVehicle("VIN56789012345678", "Ford", "Fiesta", VehicleType.CAR, LocalDate.of(2010, 10, 10), "56CD56", 1200.0, 1800.0,
                40000, LocalDate.of(2020, 9, 30), 8000);

        // Example 3
        vehicleRepository.registerVehicle("VIN90123456789012", "Volkswagen", "Golf", VehicleType.CAR, LocalDate.of(2000, 10, 22), "9012EF", 1400.0, 2100.0,
                60000, LocalDate.of(2010, 10, 1), 12000);

        // Example 4: Utility Truck for Landscaping Equipment
        vehicleRepository.registerVehicle("VLN56789012345678", "Chevrolet", "Silverado", VehicleType.CAR, LocalDate.of(2019, 8, 20), "46CD56", 2800.0, 4000.0,
                90000, LocalDate.of(2024, 8, 19), 25000);

        // Example 5: Ford F-250 for Heavy Duty Landscaping
        vehicleRepository.registerVehicle("VIN90123456789012", "Ford", "F-250", VehicleType.CAR, LocalDate.of(2018, 10, 10), "90EF90", 3200.0, 5000.0,
                120000, LocalDate.of(2024, 10, 9), 30000);

        // Example 6: Isuzu NQR for Commercial Landscaping
        vehicleRepository.registerVehicle("VIN23456789012345", "Isuzu", "NQR", VehicleType.CAR, LocalDate.of(2016, 6, 25), "23GH12", 3500.0, 6000.0,
                150000, LocalDate.of(2024, 6, 24), 35000);

        // Example 7: Mitsubishi Fuso Canter for Green Waste Collection
        vehicleRepository.registerVehicle("VIN67890123456789", "Mitsubishi", "Fuso Canter", VehicleType.CAR, LocalDate.of(2017, 7, 30), "67IJ76", 4000.0, 7000.0,
                180000, LocalDate.of(2024, 7, 29), 40000);
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
        skillRepository.createSkill("Class A Driving Licence");

        skillRepository.createSkill("Class B Driving Licence");

        skillRepository.createSkill("Class C Driving Licence");

        skillRepository.createSkill("Operation of Landscaping Machinery");

        skillRepository.createSkill("Irrigation System Installation and Maintenance");

        skillRepository.createSkill("Pruning and Trimming Techniques");

        skillRepository.createSkill("Soil Testing and Analysis");

        skillRepository.createSkill("Pest and Weed Control");

        skillRepository.createSkill("Hardscape Installation");

        skillRepository.createSkill("Tree Planting and Care");

        skillRepository.createSkill("Safety Procedures for Green Space Maintenance");

        skillRepository.createSkill("Seasonal Planting and Maintenance");
    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        jobRepository.createJob("Manager");

        jobRepository.createJob("Heavy Equipment Operator");

        jobRepository.createJob("Landscaping Technician");

        jobRepository.createJob("Irrigation Technician");

        jobRepository.createJob("Arborist");

        jobRepository.createJob("Pest Control Technician");

        jobRepository.createJob("Driver");

        jobRepository.createJob("Gardener");

        jobRepository.createJob("Plumber");

        jobRepository.createJob("Machine Operator");
    }

    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        // Example 1
        collaboratorRepository.create("Bob Smith", new Date(1990, 1, 22), 987654321,
                123456789, "bob.smith@example.com", "5678 Oak Street", "6789-100",
                "Shelbyville", DocumentType.PASSPORT, "CD2345678", new Date(2021, 5, 15),
                jobRepository.getJobList().get(0));

        // Example 2
        collaboratorRepository.create("Alice Johnson", new Date(1985, 1, 15), 123456789,
                987654321, "alice.johnson@example.com", "1234 Elm Street", "1234-125",
                "Springfield", DocumentType.PASSPORT, "AB1234567", new Date(2020, 5, 1),
                jobRepository.getJobList().get(1));

        // Example 3
        collaboratorRepository.create("Charlie Brown", new Date(1992, 1, 30), 234567891,
                876543219, "charlie.brown@example.com", "3456 Maple Street", "2345-226",
                "Centerville", DocumentType.PASSPORT, "EF3456789", new Date(2022, 5, 20),
                jobRepository.getJobList().get(2));

        // Example 4
        collaboratorRepository.create("Diana Prince", new Date(1988, 1, 7), 345678912,
                765432198, "diana.prince@example.com", "4567 Birch Street", "3456-987",
                "Metro City", DocumentType.PASSPORT, "GH4567890", new Date(2019, 5, 25),
                jobRepository.getJobList().get(3));

        // Example 5
        collaboratorRepository.create("Edward Scissorhands", new Date(1993, 1, 14), 456789123,
                654321987, "edward.scissorhands@example.com", "5678 Pine Street", "4567-908",
                "Suburbia", DocumentType.PASSPORT, "IJ5678901", new Date(2023, 1, 15),
                jobRepository.getJobList().get(4));
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

    /*private void addGreenSpaces(){
        Set<UserRole> roles = new HashSet();
        roles.add(new UserRole("GSM","GSM"));
        UserSession manager = new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("gsm@this.app"), new Password("gsm"), "Green Space Manager", AuthenticationController.ROLE_GSM )));
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        greenSpaceRepository.create("Jardim", GreenSpaceType.GARDEN, 23.4 ,manager,new Address("Rua Boda", "Porto", "4433-044"));

    }*/
}