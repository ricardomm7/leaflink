package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.time.LocalDate;
import java.util.List;

/**
 * The PostponeAgendaEntryController class handles the logic for postponing agenda entries and notifying the assigned team.
 */
public class PostponeAgendaEntryController {

    private final EntryRepository entryRepository;

    /**
     * Constructs a new PostponeAgendaEntryController object and initializes the EntryRepository.
     */
    public PostponeAgendaEntryController() {
        Repositories repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
    }

    /**
     * Retrieves the list of AgendaEntryDto objects associated with the given Green Space Manager (GSM).
     *
     * @param GSM the UserSession object representing the Green Space Manager
     * @return the list of AgendaEntryDto objects associated with the GSM
     */
    public List<AgendaEntryDto> getAgendaEntryList(UserSession GSM) {
        return entryRepository.getAgendaEntryListByGSM(GSM);
    }

    /**
     * Postpones an agenda entry to a new date and notifies the assigned team.
     *
     * @param agendaEntryDto the AgendaEntryDto object representing the agenda entry to be postponed
     * @param newDate        the new date for the agenda entry
     * @return true if the postponement is successful, false otherwise
     */
    public boolean postponeEntry(AgendaEntryDto agendaEntryDto, LocalDate newDate) {
        boolean success = entryRepository.updateAgendaEntry(agendaEntryDto, newDate, ProgressStatus.POSTPONED);
        if (success) {
            notifyTeam(agendaEntryDto, newDate);
        }
        return success;
    }

    /**
     * Notifies the team assigned to the given agenda entry about the new date.
     *
     * @param agendaEntryDto the AgendaEntryDto object representing the agenda entry
     * @param newDate        the new date for the agenda entry
     * @return true if the team notification is successful, false otherwise
     */
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

    /**
     * Converts an AgendaEntryDto object to an AgendaEntry object.
     *
     * @param agendaEntryDto the AgendaEntryDto object to be converted
     * @return the corresponding AgendaEntry object
     */
    private AgendaEntry convertToAgendaEntry(AgendaEntryDto agendaEntryDto) {
        return AgendaEntryMapper.toDomain(agendaEntryDto);
    }

    /**
     * Converts an AgendaEntry object to an AgendaEntryDto object.
     *
     * @param agendaEntry the AgendaEntry object to be converted
     * @return the corresponding AgendaEntryDto object
     */
    private AgendaEntryDto convertToAgendaEntryDto(AgendaEntry agendaEntry) {
        return AgendaEntryMapper.toDto(agendaEntry);
    }
}