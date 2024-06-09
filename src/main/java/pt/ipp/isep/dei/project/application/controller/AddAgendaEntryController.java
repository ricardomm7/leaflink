package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.List;


/**
 * Controller class responsible for adding and managing agenda entries.
 */
public class AddAgendaEntryController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;

    /**
     * Constructs an instance of AddAgendaEntryController.
     * Initializes the repositories and entry repository.
     */
    public AddAgendaEntryController() {
        repositories = Repositories.getInstance();
        entryRepository = repositories.getEntryRepository();
    }

    /**
     * Retrieves the list of ToDo entries as DTOs.
     *
     * @return a list of ToDoEntryDto representing the ToDo entries.
     */
    public List<ToDoEntryDto> getToDoEntry() {
        return ToDoEntryMapper.toDtoList(entryRepository.getToDoEntryList());
    }

    /**
     * Adds a new agenda entry.
     * Converts the provided DTO to a domain object and adds it to the repository.
     * Also removes the corresponding ToDo entry from the repository if it exists.
     *
     * @param entry the AgendaEntryDto to be added.
     */
    public void addAgendaEntry(AgendaEntryDto entry) {
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(entry);
        entryRepository.addAgendaEntry(agendaEntry);

        ToDoEntry toDoEntry = entryRepository.findToDoEntryByTitle(entry.getTitle());
        if (toDoEntry != null) {
            entryRepository.removeToDoEntry(toDoEntry);
        }
    }

    /**
     * Retrieves the list of agenda entries for a specific user session.
     *
     * @param GSM the UserSession representing the user.
     * @return a list of AgendaEntryDto representing the agenda entries for the user.
     */
    public List<AgendaEntryDto> getAgendaEntries(UserSession GSM) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail()));
    }
}
