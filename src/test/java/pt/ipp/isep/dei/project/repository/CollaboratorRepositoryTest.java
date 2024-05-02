package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Job;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorRepositoryTest {

    @Test
    void testCreateCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Software Developer");
        Date birthdate = new Date();
        Date admissionDate = new Date(2024,1,20);
        collaboratorRepository.create("John Doe", birthdate, 123456789, 987654321, "john.doe@example.com", "123 Main St", "1234-123", "City", "ID", "123456789", admissionDate, job);

        List<Collaborator> collaboratorList = collaboratorRepository.getCollaboratorList();

        assertFalse(collaboratorList.isEmpty());
        assertEquals("John Doe", collaboratorList.get(0).getName());
    }

    @Test
    void testCreateDuplicateCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Software Developer");
        Date birthdate = new Date();
        Date admissionDate = new Date(2024,1,20);
        collaboratorRepository.create("John Doe", birthdate, 123456789, 987654321, "john.doe@example.com", "123 Main St", "1234-123", "City", "ID", "123456789", admissionDate, job);

        // Trying to add duplicate collaborator
        collaboratorRepository.create("John Doe", birthdate, 123456789, 987654321, "john.doe@example.com", "123 Main St", "1234-123", "City", "ID", "123456789", admissionDate, job);

        List<Collaborator> collaboratorList = collaboratorRepository.getCollaboratorList();

        assertEquals(1, collaboratorList.size());
    }

    @Test
    void testGetCollaboratorList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job1 = new Job("Software Developer");
        Job job2 = new Job("Data Analyst");
        Date birthdate1 = new Date();
        Date birthdate2 = new Date();
        Date admissionDate1 = new Date(2024,1,20);
        Date admissionDate2 = new Date(2022,1,20);
        collaboratorRepository.create("John Doe", birthdate1, 123456789, 987654321, "john.doe@example.com", "123 Main St", "1234-123", "City", "ID", "123456789", admissionDate1, job1);
        collaboratorRepository.create("Jane Smith", birthdate2, 987654321, 123456789, "jane.smith@example.com", "456 Oak St", "1234-123", "Town", "ID", "987654321", admissionDate2, job2);

        List<Collaborator> collaboratorList = collaboratorRepository.getCollaboratorList();

        assertEquals(2, collaboratorList.size());
        assertEquals("John Doe", collaboratorList.get(0).getName());
        assertEquals("Jane Smith", collaboratorList.get(1).getName());
    }
}
