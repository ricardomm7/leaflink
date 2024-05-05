package pt.ipp.isep.dei.project.repository;

public class Repositories {

    private static Repositories instance;
    private final VehicleRepository vehicleRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final AuthenticationRepository authenticationRepository;

    private Repositories() {
        vehicleRepository = new VehicleRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        maintenanceRepository = new MaintenanceRepository();
        skillRepository = new SkillRepository();
        teamRepository = new TeamRepository();
        authenticationRepository = new AuthenticationRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public MaintenanceRepository getMaintenanceRepository() {
        return maintenanceRepository;
    }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public VehicleRepository getVehicleRepository() {return vehicleRepository;}

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

}
