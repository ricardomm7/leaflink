package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

/**
 * The RegisterGreenSpaceController class is responsible for handling the registration of new green spaces.
 * It interacts with the GreenSpaceRepository to store the created green spaces.
 */
public class RegisterGreenSpaceController {
    private final GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a new RegisterGreenSpaceController object.
     * Initializes the repositories and the greenSpaceRepository.
     */
    public RegisterGreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    public void createNewGS(GreenSpaceDto g1) {
        greenSpaceRepository.create(g1);
    }

}
