package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.NotificationService;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.List;

public class CancelAgendaEntryController {
    private Repositories repositories;
    private EntryRepository entryRepository;

    public CancelAgendaEntryController() {
        Repositories repositories = Repositories.getInstance();
        EntryRepository entryRepository = repositories.getEntryRepository();
    }

    public List<AgendaEntryDto> getAgendaEntryList(UserSession GSM) {
        List<AgendaEntry> agendaEntry = entryRepository.getAgendaEntryListByGSM(GSM);
        return AgendaEntryMapper.toDtoList(agendaEntry);
    }

    public void cancelAgendaEntry(AgendaEntryDto agendaEntryDto) {
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(agendaEntryDto);
        entryRepository.cancelAgendaEntry(agendaEntry);
    }

    public boolean notifyTeam(AgendaEntry agendaEntry) {
        boolean flag = false;
        Team team = NotificationService.getTeamByEntry(agendaEntry);
        List<Collaborator> collaboratorsList = NotificationService.getCollaboratorsList(team);
        if (collaboratorsList != null) {
            flag = NotificationService.notifyTeamCancel(collaboratorsList, agendaEntry);
        }
        return flag;
    }
}
