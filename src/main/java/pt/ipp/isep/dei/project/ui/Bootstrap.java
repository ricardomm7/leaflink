/**
 * The type Bootstrap.
 */
package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.mappers.JobMapper;
import pt.ipp.isep.dei.project.repository.*;

import java.io.*;
import java.time.LocalDate;

public class Bootstrap implements Runnable {

    /**
     * Runs the bootstrap process.
     */
    public void run() {
        addUsers();
        /*addSkills();
        addJobs();
        addVehicles();
        addCollaborators();
        addMaintenance();
        addGreenSpaces();
        addEntries();
        addAgendaEntries();*/
    }

    /**
     * Serializes all data to a file.
     */
    public void serializeAll() {
        try {
            FileOutputStream fileOut = new FileOutputStream("serial/repos.leaflink");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(Repositories.getInstance());
            outStream.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Error saving all the data " + i.getMessage());
        }
    }

    /**
     * Deserializes all data from a file.
     */
    public void deserializeAll() {
        try {
            FileInputStream fileIn = new FileInputStream("serial/repos.leaflink");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories instance = (Repositories) in.readObject();
            Repositories.setInstance(instance);
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            Repositories.setInstance(new Repositories());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading all the data " + e.getMessage());
        }
    }


    /**
     * Add vehicles.
     */
    public void addVehicles() {
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

        // Example 1
        vehicleRepository.registerVehicle(new VehicleDto("VIN12345678901234", "Toyota", "Corolla", VehicleType.CAR, LocalDate.of(2013, 3, 15), "12AB12", 1500.0, 2000.0, 50000, LocalDate.of(2023, 3, 12), 10000));

        // Example 2
        vehicleRepository.registerVehicle(new VehicleDto("VIN56789012345678", "Ford", "Fiesta", VehicleType.CAR, LocalDate.of(2010, 10, 10), "56CD56", 1200.0, 1800.0, 40000, LocalDate.of(2020, 9, 30), 8000));

        // Example 3
        vehicleRepository.registerVehicle(new VehicleDto("AIU1qasw23edfr45t", "Volkswagen", "Golf", VehicleType.CAR, LocalDate.of(2000, 10, 22), "9012EF", 1400.0, 2100.0, 60000, LocalDate.of(2010, 10, 1), 12000));
    }

    /**
     * Adds maintenance records for vehicles.
     */
    private void addMaintenance() {
        try {
            MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();

            // Example 1: Maintenance for Ford Transit
            maintenanceRepository.createMaintenance(new MaintenanceDto("12AB12", LocalDate.of(2021, 6, 10), 25000));

            // Example 2: Maintenance for Utility Truck
            maintenanceRepository.createMaintenance(new MaintenanceDto("56CD56", LocalDate.of(2021, 6, 15), 6500));

            // Example 3: Maintenance for Ford F-250
            maintenanceRepository.createMaintenance(new MaintenanceDto("9012EF", LocalDate.of(2021, 6, 20), 12500));

            // Example 4: Maintenance for Isuzu NQR
            maintenanceRepository.createMaintenance(new MaintenanceDto("23GH12", LocalDate.of(2021, 6, 25), 11000));

            // Example 5: Maintenance for Mitsubishi Fuso Canter
            maintenanceRepository.createMaintenance(new MaintenanceDto("67IJ76", LocalDate.of(2021, 7, 1), 19000));
        } catch (Exception e) {
            ShowError.showAlert("Maintenance", e.getMessage(), "Error");
        }
    }

    /**
     * Adds ToDo entries for green spaces.
     */
    private void addEntries() {
        EntryRepository entryRepository = Repositories.getInstance().getEntryRepository();
        try {
            entryRepository.createNewToDoEntry(new ToDoEntryDto("Prunning trees Teste", "maasdasdchines required", 2, UrgencyStatus.LOW, new GreenSpaceDto("Alamenda", GreenSpaceType.MEDIUM_SIZED_PARK, 342, "admin@this.app", "address", "city", "8888-000")));
            //entryRepository.createNewToDoEntry(new ToDoEntry("Watering flowers", "automatic irrigation system needed", 1, UrgencyStatus.HIGH, new GreenSpace("Botanical Garden", GreenSpaceType.LARGE_SIZED_PARK, 500, "b@b.com", new Address("123 Main St", "Metropolis", "1234-005"))));
            //entryRepository.createNewToDoEntry(new ToDoEntry("Picking up litter", "trash bags and gloves required", 3, UrgencyStatus.MEDIUM, new GreenSpace("Central Park", GreenSpaceType.LARGE_SIZED_PARK, 843, "c@c.com", new Address("456 Elm St", "Cityville", "5432-001"))));
            //entryRepository.createNewToDoEntry(new ToDoEntry("Planting new trees", "saplings and shovels needed", 2, UrgencyStatus.LOW, new GreenSpace("Community Garden", GreenSpaceType.GARDEN, 100, "d@d.com", new Address("789 Oak St", "Townsville", "6789-000"))));
            //entryRepository.createNewToDoEntry(new ToDoEntry("Mowing the lawn", "lawnmower and trimmer required", 2, UrgencyStatus.MEDIUM, new GreenSpace("City Park", GreenSpaceType.MEDIUM_SIZED_PARK, 250, "e@e.com", new Address("101 Pine St", "Villageville", "1357-009"))));
            //entryRepository.createNewToDoEntry(new ToDoEntry("Trimming bushes", "hedge trimmer needed", 1, UrgencyStatus.HIGH, new GreenSpace("Riverside Park", GreenSpaceType.MEDIUM_SIZED_PARK, 400, "f@f.com", new Address("202 Cedar St", "Riverdale", "9753-001"))));
        } catch (Exception e) {
            ShowError.showAlert("Add entries", e.getMessage(), "Error");
        }
    }

    private void addAgendaEntries() {
        EntryRepository agendaEntryRepository = Repositories.getInstance().getEntryRepository();

        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Prunning trees", "machines required", 2, UrgencyStatus.LOW, new GreenSpace("Alamenda", GreenSpaceType.MEDIUM_SIZED_PARK, 342, "admin@this.app", new Address("address", "city", "8888-000"))), LocalDate.of(2025, 6, 10), ProgressStatus.POSTPONED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Watering flowers", "automatic irrigation system needed", 1, UrgencyStatus.HIGH, new GreenSpace("Botanical Garden", GreenSpaceType.LARGE_SIZED_PARK, 500, "admin@this.app", new Address("123 Main St", "Metropolis", "1234-005"))), LocalDate.of(2025, 6, 10), ProgressStatus.POSTPONED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Picking up litter", "trash bags and gloves required", 3, UrgencyStatus.MEDIUM, new GreenSpace("Central Park", GreenSpaceType.LARGE_SIZED_PARK, 843, "admin@this.app", new Address("456 Elm St", "Cityville", "5432-001"))), LocalDate.of(2025, 6, 10), ProgressStatus.POSTPONED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Planting new trees", "saplings and shovels needed", 2, UrgencyStatus.LOW, new GreenSpace("Community Garden", GreenSpaceType.GARDEN, 100, "admin@this.app", new Address("789 Oak St", "Townsville", "6789-000"))), LocalDate.of(2025, 6, 10), ProgressStatus.POSTPONED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Mowing the lawn", "lawnmower and trimmer required", 2, UrgencyStatus.MEDIUM, new GreenSpace("City Park", GreenSpaceType.MEDIUM_SIZED_PARK, 250, "admin@this.app", new Address("101 Pine St", "Villageville", "1357-009"))), LocalDate.of(2025, 6, 10), ProgressStatus.POSTPONED));
    }

    /**
     * Adds skills to the system.
     */
    private void addSkills() {
        try {
            SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

            // Example 1
            skillRepository.createSkill(new SkillDto("Class A Driving Licence"));

            skillRepository.createSkill(new SkillDto("Class B Driving Licence"));

            skillRepository.createSkill(new SkillDto("Class C Driving Licence"));

            skillRepository.createSkill(new SkillDto("Operation of Landscaping Machinery"));

            skillRepository.createSkill(new SkillDto("Irrigation System Installation and Maintenance"));

            skillRepository.createSkill(new SkillDto("Pruning and Trimming Techniques"));

            skillRepository.createSkill(new SkillDto("Soil Testing and Analysis"));

            skillRepository.createSkill(new SkillDto("Pest and Weed Control"));

            skillRepository.createSkill(new SkillDto("Hardscape Installation"));

            skillRepository.createSkill(new SkillDto("Tree Planting and Care"));

            skillRepository.createSkill(new SkillDto("Safety Procedures for Green Space Maintenance"));

            skillRepository.createSkill(new SkillDto("Seasonal Planting and Maintenance"));
        } catch (Exception e) {
            ShowError.showAlert("Skill", e.getMessage(), "Error");
        }
    }

    /**
     * Adds jobs to the system.
     */
    private void addJobs() {
        try {
            JobRepository jobRepository = Repositories.getInstance().getJobRepository();

            jobRepository.createJob(new JobDto("Manager"));

            jobRepository.createJob(new JobDto("Heavy Equipment Operator"));

            jobRepository.createJob(new JobDto("Landscaping Technician"));

            jobRepository.createJob(new JobDto("Irrigation Technician"));

            jobRepository.createJob(new JobDto("Arborist"));

            jobRepository.createJob(new JobDto("Pest Control Technician"));

            jobRepository.createJob(new JobDto("Driver"));

            jobRepository.createJob(new JobDto("Gardener"));

            jobRepository.createJob(new JobDto("Plumber"));

            jobRepository.createJob(new JobDto("Machine Operator"));
        } catch (Exception e) {
            ShowError.showAlert("Job", e.getMessage(), "Duplicate");
        }
    }

    /**
     * Adds collaborators to the system.
     */
    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        try {
            // Example 1
            collaboratorRepository.create(new CollaboratorDto("Bob Smith", LocalDate.of(2000, 9, 30), 987654321,
                    123456789, "bob.smith@example.com", "5678 Oak Street", "6789-100",
                    "Shelbyville", DocumentType.PASSPORT, "CD2345678", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(0))));

            // Example 2
            collaboratorRepository.create(new CollaboratorDto("Alice Johnson", LocalDate.of(2000, 9, 30), 123456789,
                    987654321, "alice.johnson@example.com", "1234 Elm Street", "1234-125",
                    "Springfield", DocumentType.PASSPORT, "AB1234567", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(1))));

            // Example 3
            collaboratorRepository.create(new CollaboratorDto("Charlie Brown", LocalDate.of(2000, 9, 30), 234567891,
                    876543219, "charlie.brown@example.com", "3456 Maple Street", "2345-226",
                    "Centerville", DocumentType.PASSPORT, "EF3456789", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(2))));

            // Example 4
            collaboratorRepository.create(new CollaboratorDto("Diana Prince", LocalDate.of(2000, 9, 30), 345678912,
                    765432198, "diana.prince@example.com", "4567 Birch Street", "3456-987",
                    "Metro City", DocumentType.PASSPORT, "GH4567890", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(3))));

            // Example 5
            collaboratorRepository.create(new CollaboratorDto("Edward Scissorhands", LocalDate.of(2000, 9, 30), 456789123,
                    654321987, "edward.scissorhands@example.com", "5678 Pine Street", "4567-908",
                    "Suburbia", DocumentType.PASSPORT, "IJ5678901", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(4))));

            collaboratorRepository.create(new CollaboratorDto("Collaborador Teste", LocalDate.of(2000, 9, 30), 916789123,
                    104321987, "collaborator@this.app", "5678 Pine Street", "4567-908",
                    "Suburbia", DocumentType.PASSPORT, "IP5678901", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(4))));

        } catch (
                Exception e) {
            ShowError.showAlert("Collaborator", e.getMessage(), "Error");
        }
    }

    /**
     * Adds green spaces to the system.
     */
    private void addGreenSpaces() {
        try {
            GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
            String managerSession = "gsm@this.app";
            Address address = new Address("Santa Acácia", "Uriunda", "5555-999");

            greenSpaceRepository.create(new GreenSpaceDto("Alameda", GreenSpaceType.MEDIUM_SIZED_PARK, 78876, managerSession, address.getAddress(), address.getCity(), address.getZipCode()));

            Address address2 = new Address("Rua das Flores", "Florina", "1234-567");
            greenSpaceRepository.create(new GreenSpaceDto("Jardim das Flores", GreenSpaceType.GARDEN, 5000, managerSession, address2.getAddress(), address2.getCity(), address2.getZipCode()));

            Address address3 = new Address("Avenida Central", "Centro", "8901-234");
            greenSpaceRepository.create(new GreenSpaceDto("Parque Central", GreenSpaceType.LARGE_SIZED_PARK, 150000, managerSession, address3.getAddress(), address3.getCity(), address3.getZipCode()));

            Address address4 = new Address("Estrada Verde", "Verde", "5678-901");
            greenSpaceRepository.create(new GreenSpaceDto("Bosque Verde", GreenSpaceType.MEDIUM_SIZED_PARK, 250000, managerSession, address4.getAddress(), address4.getCity(), address4.getZipCode()));

            Address address5 = new Address("Praça Azul", "Azulina", "2345-678");
            greenSpaceRepository.create(new GreenSpaceDto("Praça Azul", GreenSpaceType.GARDEN, 20000, managerSession, address5.getAddress(), address5.getCity(), address5.getZipCode()));
        } catch (Exception e) {
            ShowError.showAlert("Create green space", e.getMessage(), "Error");
        }
    }

    /**
     * Adds users and their respective roles to the authentication repository.
     */
    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);


        /**
         * Adds a user with the "Main Administrator" role.
         */
        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        /**
         * Adds a user with the "Vehicle and Equipment Fleet Manager" role.
         */
        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        /**
         * Adds a user with the "Human Resources Manager" role.
         */
        authenticationRepository.addUserWithRole("Human Resources Manager", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        /**
         * Adds a user with the "Green Space Manager" role.
         */
        authenticationRepository.addUserWithRole("Green Space Manager", "gsm@this.app", "gsm",
                AuthenticationController.ROLE_GSM);

        /**
         * Adds a user with the "Collaborator" role.
         */
        authenticationRepository.addUserWithRole("Collaborator", "collaborator@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);

        /**
         * Adds a user with the "Software Quality Assessment Team Manager" role.
         */
        authenticationRepository.addUserWithRole("Software Quality Assessment Team Manager", "qam@this.app", "qam",
                AuthenticationController.ROLE_QAM);
    }
}