package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {
    private Collaborator collaborator;

    @BeforeEach
    public void setUp() {
        // Sample data for testing
        String name = "John Doe";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-985";
        String city = "SomeCity";
        DocumentType documentType = DocumentType.PASSPORT;
        String identificationNumber = "BSD987890";
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        Job job = new Job("Software Engineer");
        collaborator = new Collaborator(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
    }

    @Test
    public void testSetName_ValidName() {
        collaborator.setName("Jane Doe");
        assertEquals("Jane Doe", collaborator.getName());
    }

    @Test
    public void testSetName_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setName("###Truma$&"));
    }

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setName(""));
    }

    @Test
    public void testSetBirthdate_ValidDate() {
        LocalDate expectedDate = LocalDate.of(1980, 1, 1);

        collaborator.setBirthdate(expectedDate);

        LocalDate actualDate = collaborator.getBirthdate();
        assertEquals(expectedDate, actualDate); // Comparação direta entre LocalDate
    }

    @Test
    public void testSetBirthdate_NullDate() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setBirthdate(null));
    }

    @Test
    public void testSetAdmissionDate_NotAdultAtAdmission() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setAdmissionDate(LocalDate.of(2002, 1, 1)));
    }

    @Test
    public void testAssignSkillsAndGetSkills() {
        Skill skill1 = new Skill("Java Programming");
        Skill skill2 = new Skill("Project Management");
        collaborator.assignSkills(new Skill[]{skill1, skill2});

        List<Skill> assignedSkills = collaborator.getSkills();

        assertTrue(assignedSkills.contains(skill1));
        assertTrue(assignedSkills.contains(skill2));
        assertEquals(2, assignedSkills.size());
    }

    @Test
    public void testAssignSkills_DuplicateSkills() {
        Skill skill1 = new Skill("Java Programming");
        collaborator.assignSkills(new Skill[]{skill1, skill1});

        List<Skill> assignedSkills = collaborator.getSkills();

        assertEquals(1, assignedSkills.size());
    }
}
