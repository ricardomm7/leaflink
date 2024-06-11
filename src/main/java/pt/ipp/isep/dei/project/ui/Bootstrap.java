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

        // Example 4
        vehicleRepository.registerVehicle(new VehicleDto("VIN90876543211234", "Honda", "Civic", VehicleType.CAR, LocalDate.of(2018, 5, 20), "78EF78", 1600.0, 2200.0, 30000, LocalDate.of(2023, 5, 20), 12000));

        // Example 5
        vehicleRepository.registerVehicle(new VehicleDto("VIN45678901234567", "Nissan", "Leaf", VehicleType.CAR, LocalDate.of(2019, 8, 14), "34GH34", 1300.0, 1700.0, 20000, LocalDate.of(2023, 8, 14), 9000));

        vehicleRepository.registerVehicle(new VehicleDto("VIN34567890123456", "Chevrolet", "Silverado", VehicleType.TRUCK, LocalDate.of(2010, 3, 18), "45IJ45", 2500.0, 3000.0, 40000, LocalDate.of(2024, 3, 18), 15000));

        vehicleRepository.registerVehicle(new VehicleDto("VINVAN34567890123", "Renault", "Kangoo", VehicleType.VAN, LocalDate.of(2018, 3, 18), "15EF15", 1500.0, 2000.0, 45000, LocalDate.of(2023, 6, 11), 11000));

        vehicleRepository.registerVehicle(new VehicleDto("VINPKT67890123456", "Ford", "Ranger", VehicleType.PICKUP_TRUCK, LocalDate.of(2011, 12, 1), "18KL18", 2500.0, 3500.0, 15000, LocalDate.of(2023, 9, 10), 8000));

        vehicleRepository.registerVehicle(new VehicleDto("VINTRC78901234567", "John Deere", "5075E", VehicleType.TRACTOR, LocalDate.of(2017, 6, 5), "19MN19", 2800.0, 4000.0, 20000, LocalDate.of(2023, 10, 5), 10000));

        vehicleRepository.registerVehicle(new VehicleDto("VINATV89012345678", "Polaris", "Sportsman 450", VehicleType.ATV, LocalDate.of(2019, 4, 20), "20OP20", 500.0, 700.0, 8000, LocalDate.of(2023, 11, 25), 4000));

        vehicleRepository.registerVehicle(new VehicleDto("VINGTC90123456789", "Kubota", "BX1880", VehicleType.GARDEN_TRACTOR, LocalDate.of(2019, 11, 10), "21QR21", 1000.0, 1500.0, 5000, LocalDate.of(2023, 12, 15), 2500));

        vehicleRepository.registerVehicle(new VehicleDto("VINLMN01234567890", "Husqvarna", "LC221RH", VehicleType.LAWN_MOWER, LocalDate.of(2012, 1, 30), "22ST22", 200.0, 300.0, 200, LocalDate.of(2024, 1, 20), 100));

        vehicleRepository.registerVehicle(new VehicleDto("VINUTV12345678901", "Gator", "XUV835M", VehicleType.UTILITY_VEHICLE, LocalDate.of(2017, 7, 7), "23UV23", 800.0, 1200.0, 7000, LocalDate.of(2024, 2, 25), 3500));
    }

    /**
     * Adds maintenance records for vehicles.
     */
    private void addMaintenance() {
        try {
            MaintenanceRepository maintenanceRepository = Repositories.getInstance().getMaintenanceRepository();

            maintenanceRepository.createMaintenance(new MaintenanceDto("12AB12", LocalDate.of(2021, 6, 10), 25000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("56CD56", LocalDate.of(2021, 6, 15), 6500));

            maintenanceRepository.createMaintenance(new MaintenanceDto("9012EF", LocalDate.of(2021, 6, 20), 12500));

            maintenanceRepository.createMaintenance(new MaintenanceDto("23GH12", LocalDate.of(2021, 6, 25), 11000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("67IJ76", LocalDate.of(2021, 7, 1), 19000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("78EF78", LocalDate.of(2022, 7, 5), 10000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("34GH34", LocalDate.of(2023, 1, 10), 15000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("45IJ45", LocalDate.of(2023, 12, 20), 20000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("15EF15", LocalDate.of(2023, 8, 20), 47000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("18KL18", LocalDate.of(2023, 11, 5), 16000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("19MN19", LocalDate.of(2023, 12, 10), 21000));

            maintenanceRepository.createMaintenance(new MaintenanceDto("20OP20", LocalDate.of(2024, 1, 15), 8500));

            maintenanceRepository.createMaintenance(new MaintenanceDto("21QR21", LocalDate.of(2024, 2, 20), 5500));

            maintenanceRepository.createMaintenance(new MaintenanceDto("22ST22", LocalDate.of(2024, 3, 25), 300));

            maintenanceRepository.createMaintenance(new MaintenanceDto("23UV23", LocalDate.of(2024, 4, 30), 7500));
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
            entryRepository.createNewToDoEntry(new ToDoEntryDto("Prunning trees Teste", "maasdasdchines required", 2, UrgencyStatus.LOW, new GreenSpaceDto("Alamenda", GreenSpaceType.MEDIUM_SIZED_PARK, 342, "gsm@this.app", "address", "city", "8888-000")));
            entryRepository.createNewToDoEntry(new ToDoEntryDto("Planting bushes", "bushes and tools needed", 3, UrgencyStatus.MEDIUM, new GreenSpaceDto("City Garden", GreenSpaceType.GARDEN, 1200, "gsm@this.app", "Boulevard St.", "Green City", "1122-334")));
            entryRepository.createNewToDoEntry(new ToDoEntryDto("Fertilizing soil", "fertilizers required", 1, UrgencyStatus.HIGH, new GreenSpaceDto("Community Park", GreenSpaceType.LARGE_SIZED_PARK, 5000, "gsm@this.app", "Community Ave.", "Metro Town", "2233-445")));
        } catch (Exception e) {
            ShowError.showAlert("Add entries", e.getMessage(), "Error");
        }
    }

    private void addAgendaEntries() {
        EntryRepository agendaEntryRepository = Repositories.getInstance().getEntryRepository();

        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Prunning trees", "machines required", 2, UrgencyStatus.LOW, new GreenSpace("Alamenda", GreenSpaceType.MEDIUM_SIZED_PARK, 342, "gsm@this.app", new Address("address", "city", "8888-000"))), LocalDate.of(2025, 6, 10), ProgressStatus.PLANNED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Watering flowers", "automatic irrigation system needed", 1, UrgencyStatus.HIGH, new GreenSpace("Botanical Garden", GreenSpaceType.LARGE_SIZED_PARK, 500, "gsm@this.app", new Address("123 Main St", "Metropolis", "1234-005"))), LocalDate.of(2025, 6, 10), ProgressStatus.PLANNED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Picking up litter", "trash bags and gloves required", 3, UrgencyStatus.MEDIUM, new GreenSpace("Central Park", GreenSpaceType.LARGE_SIZED_PARK, 843, "gsm@this.app", new Address("456 Elm St", "Cityville", "5432-001"))), LocalDate.of(2025, 6, 10), ProgressStatus.PLANNED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Planting new trees", "saplings and shovels needed", 2, UrgencyStatus.LOW, new GreenSpace("Community Garden", GreenSpaceType.GARDEN, 100, "gsm@this.app", new Address("789 Oak St", "Townsville", "6789-000"))), LocalDate.of(2025, 6, 10), ProgressStatus.PLANNED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Mowing the lawn", "lawnmower and trimmer required", 2, UrgencyStatus.MEDIUM, new GreenSpace("City Park", GreenSpaceType.MEDIUM_SIZED_PARK, 250, "gsm@this.app", new Address("101 Pine St", "Villageville", "1357-009"))), LocalDate.of(2025, 6, 10), ProgressStatus.PLANNED));
        agendaEntryRepository.addAgendaEntry(new AgendaEntry(new ToDoEntry("Mowing the lawn", "lawnmower and trimmer required", 2, UrgencyStatus.MEDIUM, new GreenSpace("City Park", GreenSpaceType.MEDIUM_SIZED_PARK, 250, "admin@this.app", new Address("101 Pine St", "Villageville", "1357-009"))), LocalDate.of(2024, 7, 10), ProgressStatus.PLANNED));

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

            skillRepository.createSkill(new SkillDto("Herbicide Application"));

            skillRepository.createSkill(new SkillDto("Basic Carpentry"));

            skillRepository.createSkill(new SkillDto("Landscape Design"));

            skillRepository.createSkill(new SkillDto("Greenhouse Management"));

            skillRepository.createSkill(new SkillDto("Water Feature Installation and Maintenance"));
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

            jobRepository.createJob(new JobDto("Horticulturist"));

            jobRepository.createJob(new JobDto("Groundskeeper"));

            jobRepository.createJob(new JobDto("Garden Designer"));

            jobRepository.createJob(new JobDto("Landscape Architect"));

            jobRepository.createJob(new JobDto("Nursery Manager"));
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
            collaboratorRepository.create(new CollaboratorDto("Joao Teixeira", LocalDate.of(2000, 9, 30), 987654321,
                    123456789, "joao.teixeira@this.app", "5678 Oak Street", "6789-100",
                    "Shelbyville", DocumentType.PASSPORT, "CD2345678", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(0))));

            // Example 2
            collaboratorRepository.create(new CollaboratorDto("Berto Teixeira", LocalDate.of(2000, 9, 30), 123456789,
                    987654321, "berto.teixeira@this.app", "1234 Elm Street", "1234-125",
                    "Springfield", DocumentType.PASSPORT, "AB1234567", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(1))));

            // Example 3
            collaboratorRepository.create(new CollaboratorDto("Ricardo Teixeira", LocalDate.of(2000, 9, 30), 234567891,
                    876543219, "ricardo.teixeira@this.app", "3456 Maple Street", "2345-226",
                    "Centerville", DocumentType.PASSPORT, "EF3456789", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(2))));

            // Example 4
            collaboratorRepository.create(new CollaboratorDto("Paulo Teixeira", LocalDate.of(2000, 9, 30), 345678912,
                    765432198, "paulo.teixeira@this.app", "4567 Birch Street", "3456-987",
                    "Metro City", DocumentType.PASSPORT, "GH4567890", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(3))));

            // Example 5
            collaboratorRepository.create(new CollaboratorDto("Nelo Teixeira", LocalDate.of(2000, 9, 30), 456789123,
                    654321987, "nelo.teixeira@this.app", "5678 Pine Street", "4567-908",
                    "Suburbia", DocumentType.PASSPORT, "IJ5678901", LocalDate.of(2021, 9, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(4))));

            // Example 6
            collaboratorRepository.create(new CollaboratorDto("Jose Teixeira", LocalDate.of(1985, 4, 15), 567890123,
                    234567890, "jose.teixeira@this.app", "123 Green Road", "4567-123", "Greenfield",
                    DocumentType.PASSPORT, "LM5678901", LocalDate.of(2021, 10, 30),
                    JobMapper.toDomain(jobRepository.getJobList().get(5))));


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
         * Adds a user with the "Software Quality Assessment Team Manager" role.
         */
        authenticationRepository.addUserWithRole("Software Quality Assessment Team Manager", "qam@this.app", "qam",
                AuthenticationController.ROLE_QAM);

        /**
         * Adds a user with the "Collaborator" role.
         */
        authenticationRepository.addUserWithRole("Collaborator", "collaborator@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("João Teixeira", "joao.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Ricardo Teixeira", "ricardo.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Paulo Teixeira", "paulo.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Nelo Teixeira", "nelo.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("José Teixeira", "jose.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserWithRole("Berto Teixeira", "berto.teixeira@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
    }
}