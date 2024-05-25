package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.List;

public class RecordEntryController {
    private Repositories repositories;
    private EntryRepository entryRepository;
    private TeamRepository teamRepository;

    public RecordEntryController() {
        repositories = Repositories.getInstance();
        entryRepository = repositories.getEntryRepository();
        teamRepository = repositories.getTeamRepository();
    }

    public List<AgendaEntryDto> getAgendaEntryOfCollaboratorList(UserSession collaborator) {
        List<AgendaEntryDto> agendaEntryList = entryRepository.getAgendaEntryList();
        return teamRepository.getToDoEntriesAssignedToCollaborator(collaborator, agendaEntryList);
    }

    public boolean recordEntryCompletion(AgendaEntryDto agendaEntryDto) {
        AgendaEntry entry = AgendaEntryMapper.toDomain(agendaEntryDto);
        return entryRepository.recordToDoEntryCompletion(entry, ProgressStatus.COMPLETED);
    }
}
