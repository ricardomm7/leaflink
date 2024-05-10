package pt.ipp.isep.dei.project.domain;

/**
 * This class represents an address.
 */
public class Address {
    private String address;
    private String city;
    private String zipCode;

    /**
     * Constructs a new Address object with the specified address, city, and zip code.
     *
     * @param address the address.
     * @param city the city.
     * @param zipCode the zip code.
     */
    public Address(String address, String city, String zipCode) {
        setAddress(address);
        setCity(city);
        setZipCode(zipCode);
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode the zip code to set.
     * @throws IllegalArgumentException if the zip code format is invalid.
     */
    public void setZipCode(String zipCode) {
        if (verifyZipCode(zipCode)) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("The zip code must be in the format ####-###.");
        }
    }

    /**
     * Sets the address.
     *
     * @param address the address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city.
     *
     * @param city the city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the address.
     *
     * @return the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the city.
     *
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Verifies if the zip code has the correct format.
     *
     * @param zipCode the zip code to verify.
     * @return true if the zip code has the correct format, false otherwise.
     */
    private boolean verifyZipCode(String zipCode) {
        // Verifies if the zip code has the correct length
        if (zipCode == null || zipCode.length() != 8 || zipCode.charAt(4) != '-') {
            return false;
        }
        // Verifies if the characters in the correct positions are digits
        for (int i = 0; i < 8; i++) {
            if (i != 4 && !Character.isDigit(zipCode.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
