package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.*;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.mappers.EntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

public class PostponeAgendaEntryController {
    private Repositories repositories;

    private final EntryRepository entryRepository;

    public PostponeAgendaEntryController() {
        repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
    }

    public List<EntryDto> getEntryList(UserSession gsm) {
        List<EntryDto> entryDtoList = entryRepository.getEntryListByGSM(gsm);

        return entryDtoList;
    }


    public boolean postponeEntry(EntryDto entryDto, LocalDate newDate) {
        Entry entry = convertToEntry(entryDto);
        boolean success = entryRepository.updateEntry(entry, newDate, Status.POSTPONED);
        if (success) {
            notifyTeam(entry, newDate);
        }
        return success;
    }

    private boolean notifyTeam(Entry entry, LocalDate newDate) {
        boolean flag = false;

        Team team = NotificationService.getTeamByEntry(entry);
        List<Collaborator> collaboratorsList = NotificationService.getCollaboratorsList(team);
        if (collaboratorsList != null) {
            flag = NotificationService.notifyTeam(collaboratorsList,entry, newDate);
        }
        return flag;
    }

    private Entry convertToEntry(EntryDto entryDto) {
        return EntryMapper.toDomain(entryDto);
    }

    private EntryDto convertToEntryDto(Entry entry) {
        return EntryMapper.toDto(entry);
    }

}

