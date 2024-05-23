package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.EntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Register to do entry controller.
 */
public class RegisterToDoEntryController {

    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;
    private final EntryRepository entryRepository;

    /**
     * Constructs a new RegisterGreenSpaceController object.
     * Initializes the repositories and the greenSpaceRepository.
     */
    public RegisterToDoEntryController() {
        repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
        entryRepository = repositories.getEntryRepository();
    }

    /**
     * Get green spaces dto list.
     *
     * @return the list
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

    public void createNewEntry(EntryDto entryDto) {
        Entry entry = EntryMapper.toDomain(entryDto);
        entryRepository.create(entry);

    }
}
