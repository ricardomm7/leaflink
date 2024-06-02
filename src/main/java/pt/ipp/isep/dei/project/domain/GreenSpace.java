package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;

/**
 * The GreenSpace class represents a green space area managed by a user.
 * It includes details such as the name, type, area, manager, and address of the green space.
 */
public class GreenSpace implements Serializable {
    private String name;
    private GreenSpaceType type;
    private double area;
    private String manager;
    //private transient UserSession manager;
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
    public GreenSpace(String name, GreenSpaceType type, double area, String manager, Address address) {
        try {
            setArea(area);
            setName(name);
            setType(type);
            this.manager = manager;
            this.address = address;
        } catch (Exception e) {
            ShowError.showAlert("Greenspace", e.getMessage(), null);
        }
    }

    /**
     * Sets the green space type.
     *
     * @param type - the green space type from the enum class.
     */
    private void setType(GreenSpaceType type) {
        if (type == null) {
            throw new IllegalArgumentException("The type can't be empty!");
        } else {
            this.type = type;
        }
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
     * @return the manager's e-mail of the green space.
     */
    public String getManager() {
        return manager;
    }
}
