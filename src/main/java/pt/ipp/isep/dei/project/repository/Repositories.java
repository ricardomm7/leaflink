package pt.ipp.isep.dei.project.repository;

import java.io.Serializable;

/**
 * This class represents a singleton instance providing access to various repositories.
 */
public class Repositories implements Serializable {

    private static Repositories instance;
    private final VehicleRepository vehicleRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final transient AuthenticationRepository authenticationRepository;
    private final EntryRepository registerToDoEntryRepository;

    /**
     * Constructs a new Repositories object initializing all repositories.
     */
    public Repositories() {
        vehicleRepository = new VehicleRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        maintenanceRepository = new MaintenanceRepository();
        skillRepository = new SkillRepository();
        teamRepository = new TeamRepository();
        authenticationRepository = new AuthenticationRepository();
        greenSpaceRepository = new GreenSpaceRepository();
        registerToDoEntryRepository = new EntryRepository();

    }

    /**
     * Retrieves the singleton instance of Repositories.
     *
     * @return the singleton instance of Repositories.
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Retrieves the CollaboratorRepository.
     *
     * @return the CollaboratorRepository.
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Retrieves the JobRepository.
     *
     * @return the JobRepository.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * Retrieves the MaintenanceRepository.
     *
     * @return the MaintenanceRepository.
     */
    public MaintenanceRepository getMaintenanceRepository() {
        return maintenanceRepository;
    }

    /**
     * Retrieves the SkillRepository.
     *
     * @return the SkillRepository.
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Retrieves the TeamRepository.
     *
     * @return the TeamRepository.
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    /**
     * Retrieves the VehicleRepository.
     *
     * @return the VehicleRepository.
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Retrieves the GreenSpaceRepository.
     *
     * @return the GreenSpaceRepository.
     */
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Retrieves the AuthenticationRepository.
     *
     * @return the AuthenticationRepository.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Gets entry repository.
     *
     * @return the entry repository
     */
    public EntryRepository getEntryRepository() {
        return registerToDoEntryRepository;
    }

}
