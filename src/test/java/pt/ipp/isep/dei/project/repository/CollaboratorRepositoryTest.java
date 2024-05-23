package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.mappers.SkillMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorRepositoryTest {

    @Test
    public void testCreate_AddDuplicateCollaborator() {
        CollaboratorRepository repository = new CollaboratorRepository();

        // Sample data for testing
        String name = "John Doe";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-845";
        String city = "SomeCity";
        DocumentType documentType = DocumentType.PASSPORT;
        String identificationNumber = "123ABC666";
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        Job job = new Job("Software Engineer");

        // Create and add first collaborator
        repository.create(CollaboratorMapper.toDomain(new CollaboratorDto(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job)));

        // Try to create and add a second collaborator with the same taxpayer number
        repository.create(CollaboratorMapper.toDomain(new CollaboratorDto("Jane Doe", birthdate, contactMobile, taxpayerNumber, "jane.doe@example.com", address, zipCode, city, documentType, "456DEF", admissionDate, job)));

        // Ensure that the second collaborator is not added to the list
        List<CollaboratorDto> collaboratorList = repository.getCollaboratorList();
        assertEquals(1, collaboratorList.size());
        assertFalse(collaboratorList.contains(new CollaboratorDto("Jane Doe", birthdate, contactMobile, taxpayerNumber, "jane.doe@example.com", address, zipCode, city, documentType, "456DEF", admissionDate, job)));
    }

    @Test
    public void testAssignSkills() {
        // Arrange
        CollaboratorRepository repository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        // Sample data for testing
        String collaboratorName = "John Doe";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-845";
        String city = "SomeCity";
        DocumentType documentType = DocumentType.PASSPORT;
        String identificationNumber = "123ABC999";
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        Job job = new Job("Software Engineer");

        // Create and add a collaborator
        repository.create(CollaboratorMapper.toDomain(new CollaboratorDto(collaboratorName, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job)));

        // Create and add some skills
        Skill skill1 = new Skill("Technician");
        Skill skill2 = new Skill("Plumber");
        Skill skill3 = new Skill("Tree Pruner");

        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto(skill1.getDesignation())));
        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto(skill2.getDesignation())));
        skillRepository.createSkill(SkillMapper.toDomain(new SkillDto(skill3.getDesignation())));

        CollaboratorDto collaboratorDto = repository.getCollaboratorList().get(0);
        Collaborator collaborator = CollaboratorMapper.toDomain(collaboratorDto);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);

        // Act
        repository.assignSkills(collaborator, skills);

        // Convert collaborator back to DTO to check assigned skills
        collaboratorDto = CollaboratorMapper.toDto(collaborator);

        // Assert
        List<SkillDto> assignedSkills = collaboratorDto.getSkills();
        assertTrue(assignedSkills.contains(SkillMapper.toDto(skill1)));
        assertTrue(assignedSkills.contains(SkillMapper.toDto(skill2)));
        assertTrue(assignedSkills.contains(SkillMapper.toDto(skill3)));
    }
}
