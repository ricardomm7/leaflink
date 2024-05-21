package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.dto.EntryDto;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.GreenSpace;

/**
 * The type Entry mapper.
 */
public class EntryMapper {

    /**
     * The Green space mapper.
     */
    GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

    /**
     * To dto entry dto.
     *
     * @param entry the entry
     * @return the entry dto
     */
    public EntryDto toDto(Entry entry){
        GreenSpaceDto greenSpaceDto = greenSpaceMapper.toDto(entry.getGreenSpace());
        EntryDto e = new EntryDto(greenSpaceDto,entry.getDescription(),entry.getDegreeOfUrgency(), entry.getDuration());
        return e;
    }

    /**
     * To domain entry.
     *
     * @param entry the entry
     * @return the entry
     */
    public Entry toDomain(EntryDto entry){
        GreenSpace greenSpace = greenSpaceMapper.toDomain(entry.getGreenSpaceDto());
        Entry e = new Entry(greenSpace,entry.getDescription(),entry.getDegreeOfUrgency(),entry.getDuration());
        return e;
    }
}
