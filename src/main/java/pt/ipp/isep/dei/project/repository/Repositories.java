package pt.ipp.isep.dei.project.repository;

/**
 * This class represents a singleton instance providing access to various repositories.
 */
public class Repositories {

    private static Repositories instance;
    private final VehicleRepository vehicleRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Constructs a new Repositories object initializing all repositories.
     */
    private Repositories() {
        vehicleRepository = new VehicleRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        maintenanceRepository = new MaintenanceRepository();
        skillRepository = new SkillRepository();
        teamRepository = new TeamRepository();
        authenticationRepository = new AuthenticationRepository();
        greenSpaceRepository = new GreenSpaceRepository();
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

}
