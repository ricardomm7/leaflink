package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.util.List;

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
     * @param name    the name
     * @param street  the street
     * @param zipcode the zipcode
     * @param area    the area
     * @param city    the city
     * @param type    the type
     */
    public void createNewGS(String name, String street, String zipcode, double area, String city, GreenSpaceType type) {
        String us = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        try {
            GreenSpaceDto greenSpaceDto = new GreenSpaceDto(name, type, area, us, street, city, zipcode);
            greenSpaceRepository.create(greenSpaceDto);
        } catch (Exception e) {
            ShowError.showAlert("Create green space", e.getMessage(), "Error");
        }
    }

    /**
     * Returns the list of green spaces.
     *
     * @return the list of green spaces in DTO.
     */
    public List<GreenSpaceDto> getGreenSpaces() {
        return GreenSpaceMapper.toDtoList(greenSpaceRepository.getGreenSpaceList());
    }


    /**
     * Remove gs.
     *
     * @param index the index
     */
    public void removeGS(int index) {
        greenSpaceRepository.removeGS(index);
    }
}
