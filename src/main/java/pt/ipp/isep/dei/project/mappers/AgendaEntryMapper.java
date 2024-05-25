package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;

import java.io.Serializable;

public class AgendaEntryMapper implements Serializable {

    public AgendaEntryMapper() {
    }
    public static AgendaEntry toDomain(AgendaEntryDto agendaEntryDto){
        return new AgendaEntry(agendaEntryDto.getTitle(), agendaEntryDto.getDescription(), agendaEntryDto.getDuration(),
                agendaEntryDto.getUrgencyStatus(), GreenSpaceMapper.toDomain(agendaEntryDto.getGreenSpace()),agendaEntryDto.getStartingDate(),agendaEntryDto.getProgressStatus());

    }

    public static AgendaEntryDto toDto(AgendaEntry agendaEntry) {
        return new AgendaEntryDto(agendaEntry.getTitle(), agendaEntry.getDescription(), agendaEntry.getDuration(),
                agendaEntry.getDegreeOfUrgency(), GreenSpaceMapper.toDto(agendaEntry.getGreenSpace()), agendaEntry.getStartingDate(), agendaEntry.getProgressStatus());
    }


}
