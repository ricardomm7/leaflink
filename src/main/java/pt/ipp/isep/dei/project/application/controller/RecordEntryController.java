
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

/**
 * The RecordEntryController class handles the logic for recording the completion of agenda entries
 * and retrieving the list of agenda entries assigned to a specific collaborator.
 */
public class RecordEntryController {
    private final EntryRepository entryRepository;
    private final TeamRepository teamRepository;

    /**
     * Constructs a new RecordEntryController object and initializes the EntryRepository and TeamRepository.
     */
    public RecordEntryController() {
        Repositories repositories = Repositories.getInstance();
        entryRepository = repositories.getEntryRepository();
        teamRepository = repositories.getTeamRepository();
    }

    /**
     * Retrieves the list of AgendaEntryDto objects assigned to a specific collaborator.
     *
     * @param collaborator the UserSession object representing the collaborator
     * @return the list of AgendaEntryDto objects assigned to the collaborator
     */
    public List<AgendaEntryDto> getAgendaEntryOfCollaboratorList(UserSession collaborator) {
        List<AgendaEntryDto> agendaEntryList = entryRepository.getAgendaEntryList();
        return teamRepository.getAgendaEntriesAssignedToCollaborator(collaborator, agendaEntryList);
    }

    /**
     * Records the completion of an agenda entry by setting its progress status to COMPLETE.
     *
     * @param agendaEntryDto the AgendaEntryDto object representing the agenda entry to be completed
     * @return true if the completion is recorded successfully, false otherwise
     */
    public boolean recordEntryCompletion(AgendaEntryDto agendaEntryDto) {
        AgendaEntry entry = AgendaEntryMapper.toDomain(agendaEntryDto);
        return entryRepository.recordAgendaEntryCompletion(entry, ProgressStatus.COMPLETED);
    }
}
