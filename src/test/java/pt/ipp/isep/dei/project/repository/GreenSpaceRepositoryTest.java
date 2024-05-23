package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GreenSpaceRepositoryTest {

    private GreenSpaceRepository repository;

    @BeforeEach
    void setUp() {
        repository = new GreenSpaceRepository();
    }

    @Test
    void testCreate_AddGreenSpace() {
        UserSession manager = new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"), "Ana")));
        Address address = new Address("Street", "City", "4444-555");

        repository.create(new GreenSpaceDto("Park", GreenSpaceType.LARGE_SIZED_PARK, 10.5, manager, address.getAddress(), address.getCity(), address.getZipCode()));

        assertEquals(1, repository.getGreenSpaceList().size());
    }

    @Test
    void testCreate_DuplicateName() {
        UserSession manager = new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"), "Ana")));
        Address address = new Address("Street", "City", "5555-478");

        repository.create(new GreenSpaceDto("Park", GreenSpaceType.LARGE_SIZED_PARK, 10.5, manager, address.getAddress(), address.getCity(), address.getZipCode()));

        assertThrows(IllegalArgumentException.class, () ->
                repository.create(new GreenSpaceDto("Park", GreenSpaceType.LARGE_SIZED_PARK, 20.0, manager, address.getAddress(), address.getCity(), address.getZipCode())));
    }
}
