package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {
    private Collaborator collaborator;

    @BeforeEach
    public void setUp() {
        // Sample data for testing
        String name = "John Doe";
        Date birthdate = getDate(1990, Calendar.JANUARY, 1);
        int contactMobile = 123456789;
        int taxpayerNumber = 123456789;
        String email = "john.doe@example.com";
        String address = "123 Main St";
        String zipCode = "1234-985";
        String city = "SomeCity";
        String documentType = "ID";
        String identificationNumber = "123ABC";
        Date admissionDate = getDate(2020, Calendar.JANUARY, 1);
        Job job = new Job("Software Engineer");
        collaborator = new Collaborator(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
    }

    @Test
    public void testSetName_ValidName() {
        collaborator.setName("Jane Doe");
        assertEquals("Jane Doe", collaborator.getName());
    }

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setName(""));
    }

    @Test
    public void testSetBirthdate_ValidDate() {
        collaborator.setBirthdate(getDate(1980, Calendar.JANUARY, 1));
        assertEquals(getDate(1980, Calendar.JANUARY, 1), collaborator.getBirthdate());
    }

    @Test
    public void testSetBirthdate_NullDate() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setBirthdate(null));
    }

    // Helper method to create Date objects
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
