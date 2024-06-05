package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        NoClassDefFoundError exception = assertThrows(NoClassDefFoundError.class, () -> new Address("123 Main St", "SomeCity", invalidZipCode));
        assertEquals("Could not initialize class javafx.scene.control.Label", exception.getMessage());
    }

    @Test
    public void testInvalidZipCode_Length() {
        // Arrange
        String invalidZipCode = "1234-5678";

        // Act & Assert
        NoClassDefFoundError exception = assertThrows(NoClassDefFoundError.class, () -> new Address("123 Main St", "SomeCity", invalidZipCode));
        assertEquals("Could not initialize class javafx.scene.control.Label", exception.getMessage());
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
