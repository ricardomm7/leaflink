package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.Sortable;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The GreenSpaceRepository class is responsible for managing the collection of GreenSpace objects.
 * It provides methods for creating, retrieving, and organizing green spaces based on the logged-in user.
 */
public class GreenSpaceRepository implements Serializable {

    private final List<GreenSpace> greenSpaceList;

    /**
     * Constructs a new GreenSpaceRepository object and initializes the green space list.
     */
    public GreenSpaceRepository() {
        greenSpaceList = new ArrayList<>();
    }

    /**
     * Creates a new GreenSpace based on the provided GreenSpaceDto.
     *
     * @param d the GreenSpaceDto containing the green space data
     */
    public void create(GreenSpaceDto d) {
        GreenSpace g = GreenSpaceMapper.toDomain(d);
        try {
            if (checkForDuplicates(g)) {
                addGreenSpace(g);
            } else {
                throw new IllegalArgumentException("There is already a green space with that name/zipcode.");
            }
        } catch (Exception e) {
            ShowError.showAlert("Green Space", e.getMessage(), "Duplicate");
        }
    }


    /**
     * Adds a GreenSpace to the repository.
     *
     * @param g the GreenSpace object to be added
     */
    private void addGreenSpace(GreenSpace g) {
        greenSpaceList.add(g);
    }

    /**
     * Checks if a GreenSpace with the same name already exists in the repository.
     *
     * @param g the GreenSpace object to check for duplicates
     * @return true if no duplicate is found, false otherwise
     */
    private boolean checkForDuplicates(GreenSpace g) {
        for (GreenSpace x : greenSpaceList) {
            if (x.getName().equalsIgnoreCase(g.getName()) && x.getAddress().getZipCode().equalsIgnoreCase(g.getAddress().getZipCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the list of GreenSpaceDto objects.
     *
     * @return the list of GreenSpaceDto objects
     */
    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }

    /**
     * Gets the organized list of GreenSpaceDto objects based on the logged-in user.
     *
     * @param loggedUser the UserSession object representing the logged-in user
     * @return the organized list of GreenSpaceDto objects
     * @throws ClassNotFoundException if the sorting algorithm class is not found
     * @throws InstantiationException if an instance of the sorting algorithm class cannot be created
     * @throws IllegalAccessException if the sorting algorithm class or its null constructor is not accessible
     */
    public List<GreenSpaceDto> getOrganizedList(UserSession loggedUser) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<GreenSpace> listMatched = matchWithLoggedUser(loggedUser);

        // Get the sorting algorithm class name from ApplicationSession
        String algorithmClassName = ApplicationSession.getInstance().getSortAlgorithmClassName();

        // Dynamically load the sorting algorithm class
        Class<?> algorithmClass = Class.forName(algorithmClassName);
        Sortable sortable = (Sortable) algorithmClass.newInstance();

        if (!verifyEmptyList(listMatched)) {
            listMatched = sortable.sort(listMatched);
            return GreenSpaceMapper.toDtoList(listMatched);
        } else {
            throw new IllegalArgumentException("The matched list is empty.");
        }
    }

    /**
     * Checks if the given list of GreenSpace objects is empty.
     *
     * @param listMatched the list of GreenSpace objects to be checked
     * @return true if the list is empty, false otherwise
     */
    private boolean verifyEmptyList(List<GreenSpace> listMatched) {
        return listMatched.isEmpty();
    }

    /**
     * Matches the GreenSpace objects with the logged-in user.
     *
     * @param loggedUser the UserSession object representing the logged-in user
     * @return the list of GreenSpace objects associated with the logged-in user
     */
    private List<GreenSpace> matchWithLoggedUser(UserSession loggedUser) {
        List<GreenSpace> u = new ArrayList<>();
        for (GreenSpace g : greenSpaceList) {
            if (g.getManager() != null && g.getManager().equalsIgnoreCase(loggedUser.getUserEmail())) {
                u.add(g);
            }
        }
        return u;
    }

    /**
     * Remove the selected Greenspace.
     *
     * @param index the index
     */
    public void removeGS(int index) {
        greenSpaceList.remove(index);
    }
}
