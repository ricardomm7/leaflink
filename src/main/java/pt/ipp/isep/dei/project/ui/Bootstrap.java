package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.repository.*;

import java.util.Calendar;
import java.util.Date;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addSkills();
        addJobs();
        addVehicles();
        //addCollaborators();


    }

    private void addVehicles() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        // Example 1
        vehicleRepository.registerVehicle("VIN12345678901234", "Toyota", "Corolla", "Sedan", "AB1234", 1500.0, 2000.0,
                50000, new Date(2013, 3, 15), new Date(2023, 3, 12), 10000);

        // Example 2
        vehicleRepository.registerVehicle("VIN56789012345678", "Ford", "Fiesta", "Hatchback", "CD5678", 1200.0, 1800.0,
                40000, new Date(2010, 10, 10), new Date(2020, 9, 30), 8000);

        // Example 3
        vehicleRepository.registerVehicle("VIN90123456789012", "Volkswagen", "Golf", "Hatchback", "EF9012", 1400.0, 2100.0,
                60000, new Date(2000, 22, 10), new Date(2010, 10, 01), 12000);

    }

    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        // Example 1
        skillRepository.createSkill("Driving Licence");

        // Example 2
        skillRepository.createSkill("Programming");

        // Example 3
        skillRepository.createSkill("Management Licence");

        // Example 4
        skillRepository.createSkill("Customer Service");

        // Example 5
        skillRepository.createSkill("Mechanical Repair");

        // Example 6
        skillRepository.createSkill("Plumb Repair");

        // Example 7
        skillRepository.createSkill("Financial Analysis");

    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        // Example 1
        jobRepository.createJob("manager");

        // Example 2
        jobRepository.createJob("developer");

        // Example 3
        jobRepository.createJob("driver");

        // Example 4
        jobRepository.createJob("server");

        // Example 5
        jobRepository.createJob("plumber");
    }

    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();

        // Example 1
        collaboratorRepository.create("Bob Smith", new Date(1990, Calendar.JANUARY, 22), 987654321,
                123456789, "bob.smith@example.com", "5678 Oak Street", "6789-100",
                "Shelbyville", "ID Card", "CD2345678", new Date(2021, Calendar.MAY, 15),
                jobRepository.getJobList().get(0));

        // Example 2
        collaboratorRepository.create("Alice Johnson", new Date(1985, Calendar.JANUARY, 15), 123456789,
                987654321, "alice.johnson@example.com", "1234 Elm Street", "1234-125",
                "Springfield", "Passport", "AB1234567", new Date(2020, Calendar.MAY, 1),
                jobRepository.getJobList().get(1));

        // Example 3
        collaboratorRepository.create("Charlie Brown", new Date(1992, Calendar.JANUARY, 30), 234567891,
                876543219, "charlie.brown@example.com", "3456 Maple Street", "2345-226",
                "Centerville", "Driver License", "EF3456789", new Date(2022, Calendar.MAY, 20),
                jobRepository.getJobList().get(2));

        // Example 4
        collaboratorRepository.create("Diana Prince", new Date(1988, Calendar.JANUARY, 7), 345678912,
                765432198, "diana.prince@example.com", "4567 Birch Street", "3456-987",
                "Metro City", "ID Card", "GH4567890", new Date(2019, Calendar.MAY, 25),
                jobRepository.getJobList().get(3));

        // Example 5
        collaboratorRepository.create("Edward Scissorhands", new Date(1993, Calendar.JANUARY, 14), 456789123,
                654321987, "edward.scissorhands@example.com", "5678 Pine Street", "4567-908",
                "Suburbia", "ID Card", "IJ5678901", new Date(2023, Calendar.MAY, 15),
                jobRepository.getJobList().get(4));
    }


    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);


        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Human Resources Manager", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Collaborator", "collaborator@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
    }
}