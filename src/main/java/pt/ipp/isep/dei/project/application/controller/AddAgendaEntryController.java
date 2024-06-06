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

public class AddAgendaEntryController {
    private final Repositories repositories;
    private final EntryRepository entryRepository;

    public AddAgendaEntryController() {
        repositories = Repositories.getInstance();
        entryRepository = repositories.getEntryRepository();
    }

    public List<ToDoEntryDto> getToDoEntry() {
        return ToDoEntryMapper.toDtoList(entryRepository.getToDoEntryList());
    }

    public void addAgendaEntry(AgendaEntryDto entry) {
        AgendaEntry agendaEntry = AgendaEntryMapper.toDomain(entry);
        entryRepository.addAgendaEntry(agendaEntry);

        // Remove corresponding ToDoEntry from the repository
        ToDoEntry toDoEntry = entryRepository.findToDoEntryByTitle(entry.getTitle());
        if (toDoEntry != null) {
            entryRepository.removeToDoEntry(toDoEntry);
        }
    }


    public List<AgendaEntryDto> getAgendaEntries(UserSession GSM) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail()));
    }

}
