package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The RegisterToDoEntryController class is responsible for handling the logic related to registering ToDo entries.
 * It interacts with the repositories to manage GreenSpaces and ToDo entries.
 */
public class RegisterToDoEntryController {

    /**
     * The Repositories.
     */
    private final Repositories repositories;
    /**
     * The Green space repository.
     */
    private final GreenSpaceRepository greenSpaceRepository;
    /**
     * The Entry repository.
     */
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

        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        List<GreenSpaceDto> greenSpaceDto = GreenSpaceMapper.toDtoList(greenSpaces);
        UserSession manager = ApplicationSession.getInstance().getCurrentSession();
        for (GreenSpaceDto gs : greenSpaceDto) {
            if (manager.getUserEmail().equals(gs.getManager()))
                listToReturn.add(gs);
        }
        return listToReturn;
    }


    /**
     * Create new to do entry.
     *
     * @param title         the title
     * @param description   the description
     * @param duration      the duration
     * @param urg           the urg
     * @param greenSpaceDto the green space dto
     */
    public void createNewToDoEntry(String title, String description, int duration, UrgencyStatus urg, GreenSpaceDto greenSpaceDto) {
        ToDoEntry newEntry = new ToDoEntry(title, description, duration, urg, GreenSpaceMapper.toDomain(greenSpaceDto));
        entryRepository.createNewToDoEntry(newEntry);

    }

    /**
     * Gets to do entry.
     *
     * @return the to do entry
     */
    public List<ToDoEntryDto> getToDoEntry() {
        List<ToDoEntryDto> listToReturn = new ArrayList<>();
        for (ToDoEntry entry : entryRepository.getToDoEntryList()) {
            listToReturn.add(ToDoEntryMapper.toDto(entry));
        }
        return listToReturn;
    }


    /**
     * Remove to do entry.
     *
     * @param title the title
     * @param space the space
     */
    public void removeToDoEntry(String title, String space) {
        entryRepository.remove(title, space);
        ;
    }
}
