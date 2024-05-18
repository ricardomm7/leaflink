package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.ArrayList;
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
        DocumentType documentType = DocumentType.CITIZEN_CARD;
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
    public void testAssignSkills() {
        // Arrange
        CollaboratorRepository repository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        // Sample data for testing
        String collaboratorName = "John Doe";
        Date birthdate = new Date(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-845";
        String city = "SomeCity";
        DocumentType documentType = DocumentType.CITIZEN_CARD;
        String identificationNumber = "123ABC";
        Date admissionDate = new Date(2020, 1, 1);
        Job job = new Job("Software Engineer");

        // Create and add a collaborator
        repository.create(collaboratorName, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);

        // Create and add some skills
        Skill skill1 = new Skill("Technician");
        Skill skill2 = new Skill("Plumber");
        Skill skill3 = new Skill("Tree Pruner");

        skillRepository.createSkill(skill1.getDesignation());
        skillRepository.createSkill(skill2.getDesignation());
        skillRepository.createSkill(skill3.getDesignation());

        Collaborator collaborator = repository.getCollaboratorList().get(0);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);

        // Act
        repository.assignSkills(collaborator, skills);

        // Assert
        List<Skill> assignedSkills = collaborator.getSkills();
        assertEquals(3, assignedSkills.size());
        assertTrue(assignedSkills.contains(skill1));
        assertTrue(assignedSkills.contains(skill2));
        assertTrue(assignedSkills.contains(skill3));
    }

}
