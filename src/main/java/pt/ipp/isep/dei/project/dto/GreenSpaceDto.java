package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.util.Objects;

/**
 * The type Green space dto.
 */
public class GreenSpaceDto {

    private String name;
    private GreenSpaceType type;
    private double area;

    //Se quiseres faz o UserSessionDto
    private UserSession manager;

    //Em vez de criar o AddressDto
    private String address;
    private String city;
    private String zipCode;

    /**
     * Instantiates a new Green space dto.
     *
     * @param name    the name
     * @param type    the type
     * @param area    the area
     * @param manager the manager
     * @param address the address
     * @param city    the city
     * @param zipCode the zip code
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(GreenSpaceType type) {
        this.type = type;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Gets manager.
     *
     * @return the manager
     */
    public UserSession getManager() {
        return manager;
    }

    /**
     * Sets manager.
     *
     * @param manager the manager
     */
    public void setManager(UserSession manager) {
        this.manager = manager;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
