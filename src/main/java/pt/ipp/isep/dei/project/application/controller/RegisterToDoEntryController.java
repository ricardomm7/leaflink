package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.mappers.EntryMapper;
import pt.ipp.isep.dei.project.mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;
import pt.ipp.isep.dei.project.domain.GreenSpace;
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
    private final GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();
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
    public List<GreenSpaceDto> getGreenSpacesDto(){
        List<GreenSpaceDto> listToReturn = new LinkedList<>();
        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();
        UserSession manager = ApplicationSession.getInstance().getCurrentSession();
        for(GreenSpace gs : greenSpaces){
            if(manager.getUserEmail().equals(gs.getManager().getUserEmail()))
                listToReturn.add(greenSpaceMapper.toDto(gs));
        }
        return listToReturn;
    }

    /**
     * Create new entry.
     *
     * @param greenSpace      the green space
     * @param description     the description
     * @param degreeOfUrgency the degree of urgency
     * @param duration        the duration
     */
    public void createNewEntry(EntryDto entryDto) {
        Entry entry = EntryMapper.toDomain(entryDto);
        entryRepository.create(entry);

    }
}
