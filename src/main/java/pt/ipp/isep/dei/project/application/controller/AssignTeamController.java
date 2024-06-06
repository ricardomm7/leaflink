package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.TeamMapper;
import pt.ipp.isep.dei.project.mappers.VehicleMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

public class AssignTeamController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;
    private final TeamRepository teamRepository;

    public AssignTeamController() {
        this.repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
        this.teamRepository = repositories.getTeamRepository();
    }

    public List<AgendaEntryDto> getAgendaEntryList(UserSession u) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(u.getUserEmail()));

    }

    public List<TeamDto> getTeamList() {
        return TeamMapper.ListToDto(teamRepository.getAvailableTeamList());
    }


    public void setTeamAvailability(int teamIndex, Boolean isAvailable) {
        teamRepository.setTeamAvailability(teamIndex, isAvailable);
    }


    public void updateEntryWithTeam(int entryIndex, TeamDto teamDtoList) {
        entryRepository.updateTeamAgendaEntry(entryIndex, teamDtoList);
    }
}
