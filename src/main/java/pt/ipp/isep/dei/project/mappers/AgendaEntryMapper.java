package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.AgendaEntry;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The AgendaEntryMapper class is responsible for mapping between the AgendaEntry domain object
 * and the AgendaEntryDto data transfer object.
 */
public class AgendaEntryMapper implements Serializable {

    public AgendaEntryMapper() {
    }

    /**
     * Converts an AgendaEntryDto object to an AgendaEntry domain object.
     *
     * @param agendaEntryDto The AgendaEntryDto object to be converted.
     * @return The corresponding AgendaEntry domain object.
     */
    public static AgendaEntry toDomain(AgendaEntryDto agendaEntryDto) {
        return new AgendaEntry(agendaEntryDto.getTitle(), agendaEntryDto.getDescription(), agendaEntryDto.getDuration(),
                agendaEntryDto.getUrgencyStatus(), GreenSpaceMapper.toDomain(agendaEntryDto.getGreenSpace()), agendaEntryDto.getStartingDate(), agendaEntryDto.getProgressStatus());
    }

    /**
     * Converts an AgendaEntry domain object to an AgendaEntryDto object.
     *
     * @param agendaEntry The AgendaEntry domain object to be converted.
     * @return The corresponding AgendaEntryDto object.
     */
    public static AgendaEntryDto toDto(AgendaEntry agendaEntry) {
        return new AgendaEntryDto(agendaEntry.getTitle(), agendaEntry.getDescription(), agendaEntry.getDuration(),
                agendaEntry.getUrgencyStatus(), GreenSpaceMapper.toDto(agendaEntry.getGreenSpace()), agendaEntry.getStartingDate(), agendaEntry.getProgressStatus());
    }

    /**
     * Converts a list of AgendaEntry domain objects to a list of AgendaEntryDto objects.
     *
     * @param agendaEntryList The list of AgendaEntry domain objects to be converted.
     * @return The corresponding list of AgendaEntryDto objects.
     */
    public static List<AgendaEntryDto> toDtoList(List<AgendaEntry> agendaEntryList) {
        List<AgendaEntryDto> result = new ArrayList<>();
        for (AgendaEntry entry : agendaEntryList) {
            AgendaEntryDto entryDto = AgendaEntryMapper.toDto(entry);
            result.add(entryDto);
        }
        return result;
    }
}

