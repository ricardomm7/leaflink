package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.mappers.AgendaEntryMapper;
import pt.ipp.isep.dei.project.mappers.ToDoEntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;

import java.util.List;

public class AddAgendaEntryController {
    private EntryRepository entryRepository = new EntryRepository();

    public List<ToDoEntryDto> getToDoEntry (){
        return ToDoEntryMapper.toDtoList(entryRepository.getToDoEntryList());
    }

    public void addAgendaEntry(AgendaEntryDto entry) {
        // Lógica para adicionar a entrada à agenda
        entryRepository.addAgendaEntry(AgendaEntryMapper.toDomain(entry));
        // Aqui você pode adicionar lógica adicional, como salvar a entrada em um banco de dados
    }

    public List<AgendaEntryDto> getEntryRepository(UserSession GSM) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail()));
    }


    public List<AgendaEntryDto> getAgendaEntries(UserSession GSM) {
        return AgendaEntryMapper.toDtoList(entryRepository.getAgendaEntryListByGSM(GSM.getUserEmail()));
    }
}
