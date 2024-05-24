package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green space mapper.
 */
public class GreenSpaceMapper implements Serializable {

    /**
     * To dto green space dto.
     *
     * @param greenSpace the green space
     * @return the green space dto
     */
    public static GreenSpaceDto toDto(GreenSpace greenSpace) {
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

    public static List<GreenSpaceDto> toDtoList(List<GreenSpace> greenSpaceList) {
        List<GreenSpaceDto> r = new ArrayList<>();
        for (GreenSpace e : greenSpaceList) {
            r.add(new GreenSpaceDto(e.getName(), e.getType(), e.getArea(), e.getManager(), e.getAddress().getAddress(), e.getAddress().getCity(), e.getAddress().getZipCode()));
        }
        return r;
    }

    public static List<GreenSpace> toDomainList(List<GreenSpaceDto> greenSpaceListDto) {
        List<GreenSpace> r = new ArrayList<>();
        for (GreenSpaceDto e : greenSpaceListDto) {
            r.add(new GreenSpace(e.getName(), e.getType(), e.getArea(), e.getManager(), new Address(e.getAddress(), e.getCity(), e.getZipCode())));
        }
        return r;
    }
}
