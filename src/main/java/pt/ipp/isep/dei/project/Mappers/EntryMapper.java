package pt.ipp.isep.dei.project.Mappers;

import pt.ipp.isep.dei.project.Dto.EntryDto;
import pt.ipp.isep.dei.project.Dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.domain.Entry;
import pt.ipp.isep.dei.project.domain.GreenSpace;

public class EntryMapper {

    GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

    public EntryDto toDto(Entry entry){
        GreenSpaceDto greenSpaceDto = greenSpaceMapper.toDto(entry.getGreenSpace());
        EntryDto e = new EntryDto(greenSpaceDto,entry.getDescription(),entry.getDegreeOfUrgency(), entry.getDuration());
        return e;
    }

    public Entry toDomain(EntryDto entry){
        GreenSpace greenSpace = greenSpaceMapper.toDomain(entry.getGreenSpaceDto());
        Entry e = new Entry(greenSpace,entry.getDescription(),entry.getDegreeOfUrgency(),entry.getDuration());
        return e;
    }
}
