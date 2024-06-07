package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * The ListGreenSpacesController class handles the logic for retrieving and organizing the list of green spaces
 * associated with a logged-in user.
 */
public class ListGreenSpacesController {
    private final GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a new ListGreenSpacesController object and initializes the repositories.
     */
    public ListGreenSpacesController() {
        Repositories repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    /**
     * Retrieves the list of GreenSpaceDto objects associated with the logged-in user.
     *
     * @return the list of GreenSpaceDto objects associated with the logged-in user
     */
    public List<GreenSpaceDto> getList() {
        UserSession loggedUser = ApplicationSession.getInstance().getCurrentSession();
        try {
            return greenSpaceRepository.getOrganizedList(loggedUser);
        } catch (IllegalArgumentException e) {
            System.out.println("There are no green spaces associated with the logged-in user.");
            return new ArrayList<>();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
