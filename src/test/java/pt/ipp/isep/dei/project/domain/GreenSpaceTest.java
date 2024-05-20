package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import static org.junit.jupiter.api.Assertions.*;

class GreenSpaceTest {

    @Test
    void testConstructorAndGetters() {
        Address address = new Address("Street", "City", "9999-999");
        pt.isep.lei.esoft.auth.UserSession a1 = new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"));
        UserSession manager = new UserSession(a1);
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, manager, address);

        assertEquals("Park", greenSpace.getName());
        assertEquals(GreenSpaceType.MEDIUM_SIZED_PARK, greenSpace.getType());
        assertEquals(10.5, greenSpace.getArea());
        assertEquals(manager, greenSpace.getManager());
        assertEquals(address, greenSpace.getAddress());
    }

    @Test
    void testSetName_ValidName() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        greenSpace.setName("New Park");
        assertEquals("New Park", greenSpace.getName());
    }

    @Test
    void testSetName_NullName() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        assertThrows(IllegalArgumentException.class, () -> greenSpace.setName(null));
    }

    @Test
    void testSetName_EmptyName() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        assertThrows(IllegalArgumentException.class, () -> greenSpace.setName(""));
    }

    @Test
    void testSetArea_ValidArea() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        greenSpace.setArea(20.5);
        assertEquals(20.5, greenSpace.getArea());
    }

    @Test
    void testSetArea_NegativeArea() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        assertThrows(IllegalArgumentException.class, () -> greenSpace.setArea(-5.0));
    }

    @Test
    void testSetArea_ZeroArea() {
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceType.MEDIUM_SIZED_PARK, 10.5, new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"),"Ana"))), new Address("Street", "City", "9999-999"));
        assertThrows(IllegalArgumentException.class, () -> greenSpace.setArea(0));
    }
}
