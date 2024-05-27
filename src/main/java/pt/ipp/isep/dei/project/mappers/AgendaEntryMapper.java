package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.domain.Vehicle;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgendaEntryMapper implements Serializable {

    public AgendaEntryMapper() {
    }

    public static AgendaEntry toDomain(AgendaEntryDto agendaEntryDto) {
        return new AgendaEntry(agendaEntryDto.getTitle(), agendaEntryDto.getDescription(), agendaEntryDto.getDuration(),
                agendaEntryDto.getUrgencyStatus(), GreenSpaceMapper.toDomain(agendaEntryDto.getGreenSpace()), agendaEntryDto.getStartingDate(), agendaEntryDto.getProgressStatus());

    }

    public static AgendaEntryDto toDto(AgendaEntry agendaEntry) {
        return new AgendaEntryDto(agendaEntry.getTitle(), agendaEntry.getDescription(), agendaEntry.getDuration(),
                agendaEntry.getUrgencyStatus(), GreenSpaceMapper.toDto(agendaEntry.getGreenSpace()), agendaEntry.getStartingDate(), agendaEntry.getProgressStatus());
    }

    public static List<AgendaEntryDto> toDtoList(List<AgendaEntry> agendaEntryList) {
        List<AgendaEntryDto> result = new ArrayList<>();
        for (AgendaEntry entry : agendaEntryList) {
            AgendaEntryDto entryDto = AgendaEntryMapper.toDto(entry);

            result.add(entryDto);
        }

        return result;
    }

}
