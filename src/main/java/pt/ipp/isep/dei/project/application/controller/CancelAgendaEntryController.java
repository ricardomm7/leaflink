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
import pt.ipp.isep.dei.project.repository.VehicleRepository;

import java.util.List;

/**
 * Controller class for handling the cancellation of agenda entries.
 */
public class CancelAgendaEntryController {
    private Repositories repositories;
    private EntryRepository entryRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Constructor for CancelAgendaEntryController.
     * Initializes the repositories and entry repository instances.
     */
    public CancelAgendaEntryController() {
        repositories = Repositories.getInstance();
        entryRepository = repositories.getEntryRepository();
        vehicleRepository = repositories.getVehicleRepository();
    }

    /**
     * Retrieves the list of agenda entries for a given user session.
     *
     * @param GSM the user session
     * @return a list of AgendaEntryDto objects
     */
    public List<AgendaEntryDto> getAgendaEntryList(UserSession GSM) {
        List<AgendaEntry> agendaEntry = entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail());
        return AgendaEntryMapper.toDtoList(agendaEntry);
    }

    /**
     * Notifies the team about the cancellation of an agenda entry.
     *
     * @param agendaEntry the agenda entry to be cancelled
     * @return true if the team was successfully notified, false otherwise
     */
    public boolean notifyTeam(AgendaEntry agendaEntry) {
        boolean flag = false;
        Team team = NotificationService.getTeamByEntry(agendaEntry);
        List<Collaborator> collaboratorsList = NotificationService.getCollaboratorsList(team);
        if (collaboratorsList != null) {
            flag = NotificationService.notifyTeamCancel(collaboratorsList, agendaEntry);
        }
        return flag;
    }

    public boolean cancelAgendaEntry(AgendaEntryDto agendaEntryDto) {
        if (entryRepository != null) { // Verifica se entryRepository não é nulo antes de usá-lo
            AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(agendaEntryDto);
            NotificationService.notifyTeamCancel(agendaEntry.getAssignedTeam().getCollaborators(), agendaEntry);
            vehicleRepository.setVehicleAvailability(agendaEntry.getAssignedVehicles(), true);
            return entryRepository.cancelAgendaEntry(agendaEntry);
        } else {
            return false; // Retorna false se entryRepository for nulo
        }
    }
}
