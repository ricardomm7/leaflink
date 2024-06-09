package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * The type List task controller.
 */
public class ListTaskController {

    private final Repositories repositories;
    private final CollaboratorRepository collaboratorRepository;
    private final TeamRepository teamRepository;
    private final EntryRepository entryRepository;

    /**
     * Instantiates a new List task controller.
     */
    public ListTaskController() {
        repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        entryRepository = repositories.getEntryRepository();
        teamRepository = repositories.getTeamRepository();

    }

    /**
     * Gets collaborator trough email.
     *
     * @param email the email
     * @return the collaborator trough email
     */
    public Collaborator getCollaboratorTroughEmail(String email) {
        for (Collaborator collaborator : collaboratorRepository.getCollaboratorList()) {
            if (collaborator.getEmail().equals(email)) {
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Gets team trough clb.
     *
     * @param clb the clb
     * @return the team trough clb
     */
    public Team getTeamTroughCLB(Collaborator clb) {
        for (Team team : teamRepository.getTeamList()) {
            if (team.getCollaborators().contains(clb)) {
                return team;
            }
        }
        return null;
    }


    /**
     * Gets dates list.
     *
     * @param beginningDate the beginning date
     * @param endDate       the end date
     * @param status        the status
     * @param collaborator  the collaborator
     * @return the dates list
     */
    public List<AgendaEntryDto> getDatesList(LocalDate beginningDate, LocalDate endDate, Collaborator collaborator) {
        Team team = getTeamTroughCLB(collaborator);
        List<AgendaEntry> entries = entryRepository.getDatesList(beginningDate, endDate, team);
        return AgendaEntryMapper.toDtoList(entries);
    }

    /**
     * Gets agenda entries.
     *
     * @param session the session
     * @return the agenda entries
     */
    public List<AgendaEntryDto> getAgendaEntries(UserSession session) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntriesAssignedToCollaborator(session));
    }
}
