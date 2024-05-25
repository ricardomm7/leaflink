package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class ListGreenSpacesController {
    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;

    public ListGreenSpacesController() {
        this.repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    public List<GreenSpaceDto> getList(UserSession loggedUser) {
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
