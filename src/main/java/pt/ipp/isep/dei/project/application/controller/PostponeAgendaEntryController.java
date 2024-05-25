package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.time.LocalDate;
import java.util.List;

public class PostponeAgendaEntryController {
    private Repositories repositories;

    private final EntryRepository entryRepository;

    public PostponeAgendaEntryController() {
        repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
    }

    public List<AgendaEntryDto> getAgendaEntryList(UserSession GSM) {
        List<AgendaEntryDto> agendaEntryDtoDtoList = entryRepository.getAgendaEntryListByGSM(GSM);

        return agendaEntryDtoDtoList;
    }


    public boolean postponeEntry(AgendaEntryDto agendaEntryDto, LocalDate newDate) {
        boolean success = entryRepository.updateAgendaEntry(agendaEntryDto, newDate, ProgressStatus.POSTPONED);
        if (success) {
            notifyTeam(agendaEntryDto, newDate);
        }
        return success;
    }

    private boolean notifyTeam(AgendaEntryDto agendaEntryDto, LocalDate newDate) {
        boolean flag = false;
        AgendaEntry agendaEntry = convertToAgendaEntry(agendaEntryDto);

        Team team = NotificationService.getTeamByEntry(agendaEntry);
        List<Collaborator> collaboratorsList = NotificationService.getCollaboratorsList(team);
        if (collaboratorsList != null) {
            flag = NotificationService.notifyTeam(collaboratorsList, agendaEntry, newDate);
        }
        return flag;
    }

    private AgendaEntry convertToAgendaEntry(AgendaEntryDto agendaEntryDto) {
        return AgendaEntryMapper.toDomain(agendaEntryDto);
    }

    private AgendaEntryDto convertToAgendaEntryDto(AgendaEntry agendaEntry) {
        return AgendaEntryMapper.toDto(agendaEntry);
    }

}

