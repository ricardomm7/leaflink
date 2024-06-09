package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;

/**
 * The Address class represents a physical address with street address, city, and zip code.
 * This class ensures that the zip code adheres to the format ####-###.
 */
public class Address implements Serializable {

    private String address;
    private String city;
    private String zipCode;

    /**
     * Constructs a new Address with the specified address, city, and zip code.
     *
     * @param address the street address.
     * @param city    the city.
     * @param zipCode the zip code, which must be in the format ####-###.
     * @throws IllegalArgumentException if the zip code does not match the required format.
     */
    public Address(String address, String city, String zipCode) {
        setAddress(address);
        setCity(city);
        setZipCode(zipCode);
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode the new zip code, which must be in the format ####-###.
     * @throws IllegalArgumentException if the zip code does not match the required format.
     */
    public void setZipCode(String zipCode) {
        if (verifyZipCode(zipCode)) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("The zip code must be in the format ####-###.");
        }
    }

    /**
     * Sets the street address.
     *
     * @param address the new street address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city of the address.
     *
     * @param city the new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the street address.
     *
     * @return the street address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the city of the address.
     *
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the zip code of the address.
     *
     * @return the zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Verifies if the zip code matches the required format ####-###.
     *
     * @param zipCode the zip code to verify.
     * @return true if the zip code matches the required format, false otherwise.
     */
    private boolean verifyZipCode(String zipCode) {
        if (zipCode == null || zipCode.length() != 8 || zipCode.charAt(4) != '-') {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (i != 4 && !Character.isDigit(zipCode.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares this address to the specified object.
     * The result is true if and only if the argument is not null and is an Address object
     * that represents the same address, city, and zip code as this object.
     *
     * @param o the object to compare this Address against
     * @return true if the given object represents an Address equivalent to this address, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return that.getAddress().equalsIgnoreCase(address) &&
                that.getZipCode().equalsIgnoreCase(zipCode) &&
                that.getCity().equalsIgnoreCase(city);
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address.
     */
    @Override
    public String toString() {
        return this.address + ", " + this.city + " (" + this.zipCode + ")";
    }
}
