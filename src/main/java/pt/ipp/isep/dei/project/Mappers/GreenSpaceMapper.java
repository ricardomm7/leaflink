package pt.ipp.isep.dei.project.Mappers;

import pt.ipp.isep.dei.project.Dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;

public class GreenSpaceMapper {

    public GreenSpaceDto toDto(GreenSpace greenSpace) {
        GreenSpaceDto gs = new GreenSpaceDto(greenSpace.getName(), greenSpace.getType(), greenSpace.getArea(), greenSpace.getManager(), greenSpace.getAddress().getAddress(), greenSpace.getAddress().getCity(), greenSpace.getAddress().getZipCode());
        return gs;
    }

    public GreenSpace toDomain (GreenSpaceDto greenSpaceDto){
        GreenSpace gs = new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getType(), greenSpaceDto.getArea(), greenSpaceDto.getManager(),new Address(greenSpaceDto.getAddress(), greenSpaceDto.getCity(), greenSpaceDto.getZipCode()));
        return gs;
    }
}
