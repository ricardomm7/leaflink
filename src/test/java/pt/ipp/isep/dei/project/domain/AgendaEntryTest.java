package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgendaEntryTest {

    private AgendaEntry agendaEntry;
    private AgendaEntry copiedAgendaEntry;
    private LocalDate startingDate;
    private ProgressStatus progressStatus;
    private Team assignedTeam;
    private List<Vehicle> assignedVehicles;
    private ToDoEntry toDoEntry;
    private GreenSpace greenSpace;

    @BeforeEach
    void setUp() {
        startingDate = LocalDate.of(2024, 6, 8);
        progressStatus = ProgressStatus.PENDING;
        List<Skill> skills = new ArrayList<>();
        List<Collaborator> collaborators = new ArrayList<>();
        assignedTeam = new Team(skills, collaborators, 1, 5);
        assignedVehicles = new ArrayList<>();
        assignedVehicles.add(new Vehicle("1HGBH41JXMN109186", "Toyota", "Corolla", VehicleType.CAR, LocalDate.of(2020, 1, 15), "AA00AA", 1500.0, 2000.0, 50000, LocalDate.of(2020, 1, 15), 12));

        greenSpace = new GreenSpace("Park", GreenSpaceType.GARDEN, 423, "a@a.com", new Address("a", "dvs", "5555-555"));

        agendaEntry = new AgendaEntry("Title", "Description", 2, UrgencyStatus.HIGH, greenSpace, startingDate, progressStatus);
        agendaEntry.setAssignedTeam(assignedTeam);
        agendaEntry.setAssignedVehicles(assignedVehicles);

        copiedAgendaEntry = new AgendaEntry(agendaEntry, LocalDate.of(2024, 6, 9), ProgressStatus.PLANNED);
        toDoEntry = new ToDoEntry("Title", "Description", 2, UrgencyStatus.HIGH, greenSpace);
    }

    @Test
    void testConstructor() {
        assertEquals("Title", agendaEntry.getTitle());
        assertEquals("Description", agendaEntry.getDescription());
        assertEquals(2, agendaEntry.getDuration());
        assertEquals(UrgencyStatus.HIGH, agendaEntry.getUrgencyStatus());
        assertEquals("Park", agendaEntry.getGreenSpace().getName());
        assertEquals(startingDate, agendaEntry.getStartingDate());
        assertEquals(progressStatus, agendaEntry.getProgressStatus());
    }

    @Test
    void testCopyConstructor() {
        assertEquals("Title", copiedAgendaEntry.getTitle());
        assertEquals("Description", copiedAgendaEntry.getDescription());
        assertEquals(2, copiedAgendaEntry.getDuration());
        assertEquals(UrgencyStatus.HIGH, copiedAgendaEntry.getUrgencyStatus());
        assertEquals("Park", copiedAgendaEntry.getGreenSpace().getName());
        assertEquals(LocalDate.of(2024, 6, 9), copiedAgendaEntry.getStartingDate());
        assertEquals(ProgressStatus.PLANNED, copiedAgendaEntry.getProgressStatus());
    }

    @Test
    void testToDoEntryConstructor() {
        AgendaEntry toDoEntryAgenda = new AgendaEntry(toDoEntry, LocalDate.of(2024, 6, 10), ProgressStatus.PLANNED);
        assertEquals("Title", toDoEntryAgenda.getTitle());
        assertEquals("Description", toDoEntryAgenda.getDescription());
        assertEquals(2, toDoEntryAgenda.getDuration());
        assertEquals(UrgencyStatus.HIGH, toDoEntryAgenda.getUrgencyStatus());
        assertEquals("Park", toDoEntryAgenda.getGreenSpace().getName());
        assertEquals(LocalDate.of(2024, 6, 10), toDoEntryAgenda.getStartingDate());
        assertEquals(ProgressStatus.PLANNED, toDoEntryAgenda.getProgressStatus());
    }

    @Test
    void testGetAndSetStartingDate() {
        LocalDate newDate = LocalDate.of(2024, 7, 1);
        agendaEntry.setStartingDate(newDate);
        assertEquals(newDate, agendaEntry.getStartingDate());
    }

    @Test
    void testGetAndSetProgressStatus() {
        ProgressStatus newStatus = ProgressStatus.COMPLETED;
        agendaEntry.setProgressStatus(newStatus);
        assertEquals(newStatus, agendaEntry.getProgressStatus());
    }

    @Test
    void testGetAndSetAssignedTeam() {
        List<Skill> newSkills = new ArrayList<>();
        List<Collaborator> newCollaborators = new ArrayList<>();
        Team newTeam = new Team(newSkills, newCollaborators, 2, 10);
        agendaEntry.setAssignedTeam(newTeam);
        assertEquals(newTeam, agendaEntry.getAssignedTeam());
    }

    @Test
    void testGetAndSetAssignedVehicles() {
        List<Vehicle> newVehicles = new ArrayList<>();
        newVehicles.add(new Vehicle("2HGCM82633A123456", "Honda", "Civic", VehicleType.CAR, LocalDate.of(2021, 5, 10), "AA00AA", 1400.0, 1900.0, 30000, LocalDate.of(2021, 5, 10), 12));
        agendaEntry.setAssignedVehicles(newVehicles);
        assertEquals(newVehicles, agendaEntry.getAssignedVehicles());
    }

    @Test
    void testSetAvailable() {
        agendaEntry.setAvailable(agendaEntry);
        assertTrue(assignedTeam.isAvailable());

        for (Vehicle vehicle : assignedVehicles) {
            assertTrue(vehicle.isAvailable());
        }
    }

}
