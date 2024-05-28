package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.application.session.UserSession;

import java.io.Serializable;

/**
 * The GreenSpace class represents a green space area managed by a user.
 * It includes details such as the name, type, area, manager, and address of the green space.
 */
public class GreenSpace implements Serializable {
    private String name;
    private GreenSpaceType type;
    private double area;
    private transient UserSession manager;
    private Address address;

    /**
     * Constructs a new GreenSpace with the specified details.
     *
     * @param name    the name of the green space.
     * @param type    the type of the green space.
     * @param area    the area of the green space in hectares.
     * @param manager the manager of the green space.
     * @param address the address of the green space.
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
     * @param name the name of the green space.
     * @throws IllegalArgumentException if the name is null or empty.
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
     * @param area the area of the green space in hectares.
     * @throws IllegalArgumentException if the area is less than or equal to 0.
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
     * @return the name of the green space.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the address of the green space.
     *
     * @return the address of the green space.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the type of the green space.
     *
     * @return the type of the green space.
     */
    public GreenSpaceType getType() {
        return type;
    }

    /**
     * Gets the area of the green space.
     *
     * @return the area of the green space in hectares.
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets the manager of the green space.
     *
     * @return the manager of the green space.
     */
    public UserSession getManager() {
        return manager;
    }
}
