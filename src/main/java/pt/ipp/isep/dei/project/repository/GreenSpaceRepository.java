package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a repository for storing green spaces.
 */
public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaceList;

    /**
     * Constructs a new GreenSpaceRepository object with an empty list.
     */
    public GreenSpaceRepository() {
        greenSpaceList = new ArrayList<>();
    }

    /**
     * Creates a new green space and adds it to the repository if it does not already exist.
     *
     * @param name    the name of the green space
     * @param type    the type of the green space
     * @param area    the area of the green space in hectares
     * @param manager the manager of the green space
     * @param address the address of the green space
     * @throws IllegalArgumentException if a green space with the same name already exists
     */
    public void create(String name, GreenSpaceType type, double area, UserSession manager, Address address) {
        GreenSpace g = new GreenSpace(name, type, area, manager, address);
        if (checkForDuplicates(g)) {
            addGreenSpace(g);
        } else {
            throw new IllegalArgumentException("There is already a green space with that name.");
        }
    }

    /**
     * Adds the specified green space to the repository.
     *
     * @param g the green space to be added
     */
    private void addGreenSpace(GreenSpace g) {
        greenSpaceList.add(g);
    }

    /**
     * Checks if a green space with the same name already exists in the repository.
     *
     * @param g the green space to be checked
     * @return true if no green space with the same name exists, false otherwise
     */
    private boolean checkForDuplicates(GreenSpace g) {
        for (GreenSpace x : greenSpaceList) {
            if (x.getName().equalsIgnoreCase(g.getName())) {
                return false;
            }
        }
        return true;
    }

    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }
}
