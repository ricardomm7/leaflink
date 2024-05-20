package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.Dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.Mappers.GreenSpaceMapper;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.DegreeOfUrgency;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.LinkedList;
import java.util.List;

public class RegisterToDoEntryController {

    private final Repositories repositories;
    private final GreenSpaceRepository greenSpaceRepository;
    private final GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

    /**
     * Constructs a new RegisterGreenSpaceController object.
     * Initializes the repositories and the greenSpaceRepository.
     */
    public RegisterToDoEntryController() {
        repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }

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
    public void createNewEntry(GreenSpaceDto greenSpace, String description, DegreeOfUrgency degreeOfUrgency, String duration) {
        //EntryRepository.create(greenSpace, description,degreeOfUrgency,duration));
    }
}
