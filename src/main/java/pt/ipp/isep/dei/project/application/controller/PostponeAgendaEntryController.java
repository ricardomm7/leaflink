package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.NotificationService;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.ui.ShowError;

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
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail()));
    }

    /**
     * Postpones an agenda entry to a new date and notifies the assigned team.
     *
     * @param agendaEntryDto the AgendaEntry object representing the agenda entry to be postponed
     * @param newDate        the new date for the agenda entry
     * @return true if the postponement is successful, false otherwise
     */
    public boolean postponeEntry(AgendaEntryDto agendaEntryDto, LocalDate newDate) {
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(agendaEntryDto);
        boolean success = entryRepository.updateAgendaEntry(agendaEntry, newDate, ProgressStatus.POSTPONED);
        if (success) {
            notifyTeam(agendaEntry, newDate);
        }
        return success;
    }

    /**
     * Notifies the team assigned to the given agenda entry about the new date.
     *
     * @param agendaEntry the AgendaEntry object representing the agenda entry
     * @param newDate     the new date for the agenda entry
     */
    private void notifyTeam(AgendaEntry agendaEntry, LocalDate newDate) {
        if (agendaEntry.getAssignedTeam() != null) {
            NotificationService.notifyTeam(agendaEntry.getAssignedTeam().getCollaborators(), agendaEntry, newDate);

        } else {
            ShowError.showAlert("Error", "There is no team assigned to this entry", "No Team");
        }

    }
}