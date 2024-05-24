package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.application.session.UserSession;

import java.io.Serializable;

/**
 * Represents a green space with a name, type, area, manager, and address.
 */
public class GreenSpace implements Serializable {
    private String name;
    private GreenSpaceType type;
    private double area;
    private transient UserSession manager;
    private Address address;

    /**
     * Constructs a new GreenSpace with the specified name, type, area, manager, and address.
     *
     * @param name    the name of the green space
     * @param type    the type of the green space
     * @param area    the area of the green space in hectares
     * @param manager the manager of the green space
     * @param address the address of the green space
     * @throws IllegalArgumentException if the name is null or empty, or if the area is not greater than 0
     */
    public GreenSpace(String name, GreenSpaceType type, double area, UserSession manager, Address address) {
        setArea(area);
        setName(name);
        this.type = type;
        this.manager = manager;
        this.address = address;
    }

    /**
     * Sets the name of the green space.
     *
     * @param name the new name of the green space
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Sets the area of the green space.
     *
     * @param area the new area of the green space in hectares
     * @throws IllegalArgumentException if the area is not greater than 0
     */
    public void setArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("The area must be larger than 0 hectares.");
        }
        this.area = area;
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
     * Retrieves the address of the green space.
     *
     * @return the address of the green space
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves the type of the green space.
     *
     * @return the type of the green space
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Retrieves the area of the green space in square meters.
     *
     * @return the area of the green space
     */
    public double getArea() {
        return area;
    }

    /**
     * Retrieves the manager of the green space.
     *
     * @return the user session of the manager
     */
    public UserSession getManager() {
        return manager;
    }
}
