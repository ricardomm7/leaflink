package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.ToDoEntryDto;

import java.util.ArrayList;
import java.util.List;

public class AddAgendaEntryController {
    private List<ToDoEntryDto> agendaEntries = new ArrayList<>();

    public void addAgendaEntry(ToDoEntryDto entry) {
        // Lógica para adicionar a entrada à agenda
        agendaEntries.add(entry);
        // Aqui você pode adicionar lógica adicional, como salvar a entrada em um banco de dados
    }

    public List<ToDoEntryDto> getAgendaEntries() {
        return agendaEntries;
    }


}
