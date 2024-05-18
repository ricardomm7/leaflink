package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

/**
 * The RegisterGreenSpaceController class is responsible for handling the registration of new green spaces.
 * It interacts with the GreenSpaceRepository to store the created green spaces.
 */
public class RegisterGreenSpaceController {
    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a new RegisterGreenSpaceController object.
     * Initializes the repositories and the greenSpaceRepository.
     */
    public RegisterGreenSpaceController() {
        repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

    /**
     * Creates a new green space with the provided information.
     * If the input is valid, adds the green space to the repository.
     *
     * @param name    the name of the green space
     * @param type    the type of the green space
     * @param area    the area of the green space
     * @param manager the user session of the manager
     * @param street  the street of the green space address
     * @param zipcode the ZIP code of the green space address
     * @param city    the city of the green space address
     */
    public void createNewGS(String name, GreenSpaceType type, double area, UserSession manager, String street, String zipcode, String city) {
        greenSpaceRepository.create(name, type, area, manager, parseAddress(street, zipcode, city));
    }

    /**
     * Parses the provided address information and creates an Address object.
     *
     * @param street  the street of the address
     * @param zipcode the ZIP code of the address
     * @param city    the city of the address
     * @return the Address object created from the provided information
     */
    private Address parseAddress(String street, String zipcode, String city) {
        return new Address(street, city, zipcode);
    }
}
