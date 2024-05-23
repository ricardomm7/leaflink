package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.Status;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.mappers.EntryMapper;
import pt.ipp.isep.dei.project.repository.EntryRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

public class PostponeAgendaEntryController {
    private Repositories repositories;

    private final EntryRepository entryRepository;
    private final TeamRepository teamRepository;

    public PostponeAgendaEntryController() {
        repositories = Repositories.getInstance();
        this.entryRepository = repositories.getEntryRepository();
        this.teamRepository = repositories.getTeamRepository();
    }

    public List<EntryDto> getEntryList(UserSession gsm) {
        List<EntryDto> entryDtoList = entryRepository.getEntryListByGSM(gsm);

        return entryDtoList;
    }


    public boolean postponeEntry(EntryDto entryDto, LocalDate newDate) {
        Entry entry = convertToEntry(entryDto);
        return entryRepository.updateEntry(entry, newDate, Status.POSTPONED);
    }

    private Entry convertToEntry(EntryDto entryDto) {
        return EntryMapper.toDomain(entryDto);
    }

    private EntryDto convertToEntryDto(Entry entry) {
        return EntryMapper.toDto(entry);
    }

}

