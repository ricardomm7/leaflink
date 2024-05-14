package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorRepositoryTest {

    @Test
    public void testCreate_AddDuplicateCollaborator() {
        CollaboratorRepository repository = new CollaboratorRepository();

        // Sample data for testing
        String name = "John Doe";
        Date birthdate = new Date(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-845";
        String city = "SomeCity";
        String documentType = "ID";
        String identificationNumber = "123ABC";
        Date admissionDate = new Date(2020, 1, 1);
        Job job = new Job("Software Engineer");

        // Create and add first collaborator
        repository.create(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);

        // Try to create and add a second collaborator with the same taxpayer number
        repository.create("Jane Doe", birthdate, contactMobile, taxpayerNumber, "jane.doe@example.com", address, zipCode, city, documentType, "456DEF", admissionDate, job);

        // Ensure that the second collaborator is not added to the list
        List<Collaborator> collaboratorList = repository.getCollaboratorList();
        assertEquals(1, collaboratorList.size());
        assertFalse(collaboratorList.contains(new Collaborator("Jane Doe", birthdate, contactMobile, taxpayerNumber, "jane.doe@example.com", address, zipCode, city, documentType, "456DEF", admissionDate, job)));
    }

    @Test
    public void testUpdateCollaborator_SuccessfulUpdate() {
        CollaboratorRepository repository = new CollaboratorRepository();

        // Sample data for testing
        String name = "John Doe";
        Date birthdate = new Date(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-875";
        String city = "SomeCity";
        String documentType = "ID";
        String identificationNumber = "123ABC";
        Date admissionDate = new Date(2020, 1, 1);
        Job job = new Job("Software Engineer");

        // Create and add a collaborator
        repository.create(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);

        // Modify some details of the collaborator
        Collaborator updatedCollaborator = new Collaborator("Jane Doe", birthdate, contactMobile, taxpayerNumber, "jane.doe@example.com", address, zipCode, city, documentType, "456DEF", admissionDate, job);

        // Update the collaborator in the repository
        repository.updateCollaborator(updatedCollaborator);

        // Ensure that the collaborator in the repository is updated
        List<Collaborator> collaboratorList = repository.getCollaboratorList();
        assertEquals(1, collaboratorList.size());
        assertFalse(collaboratorList.contains(updatedCollaborator));
    }

    // Helper method to create Date objects
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
