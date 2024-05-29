package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.LinkedList;
import java.util.List;

/**
 * The RegisterToDoEntryController class is responsible for handling the logic related to registering ToDo entries.
 * It interacts with the repositories to manage GreenSpaces and ToDo entries.
 */
public class RegisterToDoEntryController {

    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;
    private final EntryRepository entryRepository;

    /**
     * Constructs a new RegisterToDoEntryController instance.
     * Initializes the repositories required for managing GreenSpaces and ToDo entries.
     */
    public RegisterToDoEntryController() {
        repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
        entryRepository = repositories.getEntryRepository();
    }

    /**
     * Retrieves a list of GreenSpaceDto objects managed by the current user.
     *
     * @return a list of GreenSpaceDto objects managed by the current user.
     */
    public List<GreenSpaceDto> getGreenSpacesDto() {
        List<GreenSpaceDto> listToReturn = new LinkedList<>();
        List<GreenSpaceDto> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        UserSession manager = ApplicationSession.getInstance().getCurrentSession();
        for (GreenSpaceDto gs : greenSpaces) {
            if (manager.getUserEmail().equals(gs.getManager().getUserEmail()))
                listToReturn.add(gs);
        }
        return listToReturn;
    }

    /**
     * Creates a new ToDo entry by saving the provided ToDoEntryDto object.
     *
     * @param toDoEntryDto the ToDoEntryDto object to be saved.
     */
    public void createNewToDoEntry(String title, String description, int duration, UrgencyStatus urg, GreenSpaceDto greenSpaceDto) {
        entryRepository.create(new ToDoEntryDto(title, description, duration, urg, greenSpaceDto));
    }
}
