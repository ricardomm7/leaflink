package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class RepositoriesTest {

    @Test
    public void testGetInstance() {
        // Act
        Repositories instance1 = Repositories.getInstance();
        Repositories instance2 = Repositories.getInstance();

        // Assert
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetCollaboratorRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        CollaboratorRepository collaboratorRepository = repositories.getCollaboratorRepository();

        // Assert
        assertNotNull(collaboratorRepository);
    }

    @Test
    public void testGetJobRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        JobRepository jobRepository = repositories.getJobRepository();

        // Assert
        assertNotNull(jobRepository);
    }

    @Test
    public void testGetMaintenanceRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        MaintenanceRepository maintenanceRepository = repositories.getMaintenanceRepository();

        // Assert
        assertNotNull(maintenanceRepository);
    }

    @Test
    public void testGetSkillRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        SkillRepository skillRepository = repositories.getSkillRepository();

        // Assert
        assertNotNull(skillRepository);
    }

    @Test
    public void testGetTeamRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        TeamRepository teamRepository = repositories.getTeamRepository();

        // Assert
        assertNotNull(teamRepository);
    }

    @Test
    public void testGetVehicleRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        VehicleRepository vehicleRepository = repositories.getVehicleRepository();

        // Assert
        assertNotNull(vehicleRepository);
    }

    @Test
    public void testGetAuthenticationRepository() {
        // Arrange
        Repositories repositories = Repositories.getInstance();

        // Act
        AuthenticationRepository authenticationRepository = repositories.getAuthenticationRepository();

        // Assert
        assertNotNull(authenticationRepository);
    }
}
