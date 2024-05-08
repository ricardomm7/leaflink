package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    public void testValidZipCode() {
        // Arrange
        String validZipCode = "1234-567";

        // Act
        Address address = new Address("123 Main St", "SomeCity", validZipCode);

        // Assert
        assertEquals(validZipCode, address.getZipCode());
    }

    @Test
    public void testInvalidZipCode_Format() {
        // Arrange
        String invalidZipCode = "12345678";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Address("123 Main St", "SomeCity", invalidZipCode));
        assertEquals("The zip code must be in the format ####-###.", exception.getMessage());
    }

    @Test
    public void testInvalidZipCode_Length() {
        // Arrange
        String invalidZipCode = "1234-5678";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Address("123 Main St", "SomeCity", invalidZipCode));
        assertEquals("The zip code must be in the format ####-###.", exception.getMessage());
    }

    @Test
    public void testGetAddress() {
        // Arrange
        String addressText = "123 Main St";

        // Act
        Address address = new Address(addressText, "SomeCity", "1234-567");

        // Assert
        assertEquals(addressText, address.getAddress());
    }

    @Test
    public void testGetCity() {
        // Arrange
        String city = "SomeCity";

        // Act
        Address address = new Address("123 Main St", city, "1234-567");

        // Assert
        assertEquals(city, address.getCity());
    }
}
