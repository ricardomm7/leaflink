package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

/**
 * The type Green space mapper.
 */
public class GreenSpaceMapper {

    /**
     * To dto green space dto.
     *
     * @param greenSpace the green space
     * @return the green space dto
     */
    public GreenSpaceDto toDto(GreenSpace greenSpace) {
        return new GreenSpaceDto(greenSpace.getName(), greenSpace.getType(), greenSpace.getArea(), greenSpace.getManager(), greenSpace.getAddress().getAddress(), greenSpace.getAddress().getCity(), greenSpace.getAddress().getZipCode());
    }

    /**
     * To domain green space.
     *
     * @param greenSpaceDto the green space dto
     * @return the green space
     */
    public static GreenSpace toDomain(GreenSpaceDto greenSpaceDto) {
        return new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getType(), greenSpaceDto.getArea(), greenSpaceDto.getManager(), new Address(greenSpaceDto.getAddress(), greenSpaceDto.getCity(), greenSpaceDto.getZipCode()));
    }
}
