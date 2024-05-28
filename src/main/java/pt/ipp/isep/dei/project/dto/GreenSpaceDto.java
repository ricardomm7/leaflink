package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.io.Serializable;
import java.util.Objects;

/**
 * The GreenSpaceDto class represents a data transfer object for the GreenSpace domain object.
 * It encapsulates the data related to a green space and provides methods to access and manipulate this data.
 */
public class GreenSpaceDto implements Serializable {

    private final String name;
    private final GreenSpaceType type;
    private final double area;
    private final UserSession manager;
    private final String address;
    private final String city;
    private final String zipCode;

    /**
     * Constructs a new GreenSpaceDto object with the provided data.
     *
     * @param name    the name of the green space
     * @param type    the type of the green space
     * @param area    the area of the green space
     * @param manager the manager of the green space
     * @param address the address of the green space
     * @param city    the city of the green space
     * @param zipCode the zip code of the green space
     */
    public GreenSpaceDto(String name, GreenSpaceType type, double area, UserSession manager, String address, String city, String zipCode) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.manager = manager;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    /**
     * Gets the name of the green space.
     *
     * @return the name of the green space
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the green space.
     *
     * @return the type of the green space
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Gets the area of the green space.
     *
     * @return the area of the green space
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets the manager of the green space.
     *
     * @return the manager of the green space
     */
    public UserSession getManager() {
        return manager;
    }

    /**
     * Gets the address of the green space.
     *
     * @return the address of the green space
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the city of the green space.
     *
     * @return the city of the green space
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the zip code of the green space.
     *
     * @return the zip code of the green space
     */
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreenSpaceDto that = (GreenSpaceDto) o;
        return Double.compare(area, that.area) == 0 && Objects.equals(name, that.name) && type == that.type && Objects.equals(manager, that.manager) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, area, manager, address, city, zipCode);
    }

    @Override
    public String toString() {
        return "GreenSpaceDto{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", area=" + area +
                ", manager=" + manager +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
