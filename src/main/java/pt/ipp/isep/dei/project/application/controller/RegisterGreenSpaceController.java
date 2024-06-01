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
     * Initializes the green space repository.
     */
    public RegisterGreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    /**
     * Creates a new green space with the provided information.
     *
     * @param greenSpaceDto the GreenSpaceDto object representing the new green space
     */
    public void createNewGS(GreenSpaceDto greenSpaceDto) {
        greenSpaceRepository.create(greenSpaceDto);
    }
}
