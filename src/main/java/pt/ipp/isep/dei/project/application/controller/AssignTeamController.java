package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.NotificationService;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.TeamMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.List;


/**
 * Controller class responsible for assigning teams to agenda entries.
 */
public class AssignTeamController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;
    private final TeamRepository teamRepository;

    /**
     * Constructs an instance of AssignTeamController.
     * Initializes the repositories and entry repository.
     */
    public AssignTeamController() {
        this.repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
        this.teamRepository = repositories.getTeamRepository();
    }


    /**
     * Retrieves the list of agenda entries for a specific user session.
     *
     * @param u the UserSession representing the user.
     * @return a list of AgendaEntryDto representing the agenda entries for the user.
     */
    public List<AgendaEntryDto> getAgendaEntryList(UserSession u) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(u.getUserEmail()));

    }


    /**
     * Retrieves the list of available teams.
     *
     * @return a list of TeamDto representing the available teams.
     */
    public List<TeamDto> getTeamList() {
        return TeamMapper.ListToDto(teamRepository.getAvailableTeamList());
    }


    /**
     * Sets the availability of a team.
     *
     * @param teamIndex   the index of the team to set availability for.
     * @param isAvailable a boolean indicating the availability status.
     */
    public void setTeamAvailability(int teamIndex, Boolean isAvailable) {
        teamRepository.setTeamAvailability(teamIndex, isAvailable);
    }


    /**
     * Updates an agenda entry with the selected team.
     *
     * @param agendaEntryDto the AgendaEntryDto to be updated.
     * @param teamDtoList    the TeamDto representing the selected team.
     */
    public void updateEntryWithTeam(AgendaEntryDto agendaEntryDto, TeamDto teamDtoList) {
        Team t = TeamMapper.toDomain(teamDtoList);
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(agendaEntryDto);
        entryRepository.updateTeamAgendaEntry(agendaEntry, t);
        NotificationService.notifyTeamCreate(t.getCollaborators(),agendaEntry);


    }

}
