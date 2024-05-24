package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.io.Serializable;

/**
 * The type Entry mapper.
 */
public class EntryMapper implements Serializable {

    /**
     * To dto entry dto.
     *
     * @param entry the entry
     * @return the entry dto
     */
    public static EntryDto toDto(Entry entry) {
        GreenSpaceDto greenSpaceDto = GreenSpaceMapper.toDto(entry.getGreenSpace());
        return new EntryDto(greenSpaceDto, entry.getDescription(), entry.getDegreeOfUrgency(), entry.getDuration());
    }

    /**
     * To domain entry.
     *
     * @param entry the entry
     * @return the entry
     */
    public static Entry toDomain(EntryDto entry) {
        GreenSpace greenSpace = GreenSpaceMapper.toDomain(entry.getGreenSpaceDto());
        return new Entry(greenSpace, entry.getDescription(), entry.getDegreeOfUrgency(), entry.getDuration());
    }
}
