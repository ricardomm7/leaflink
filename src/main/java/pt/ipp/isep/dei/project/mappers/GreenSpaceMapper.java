package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Address;
import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The GreenSpaceMapper class is responsible for mapping between the GreenSpace domain object
 * and the GreenSpaceDto data transfer object.
 */
public class GreenSpaceMapper implements Serializable {

    /**
     * Converts a GreenSpace domain object to a GreenSpaceDto object.
     *
     * @param greenSpace The GreenSpace domain object to be converted.
     * @return The corresponding GreenSpaceDto object.
     */
    public static GreenSpaceDto toDto(GreenSpace greenSpace) {
        return new GreenSpaceDto(greenSpace.getName(), greenSpace.getType(), greenSpace.getArea(), greenSpace.getManager(), greenSpace.getAddress().getAddress(), greenSpace.getAddress().getCity(), greenSpace.getAddress().getZipCode());
    }

    /**
     * Converts a GreenSpaceDto object to a GreenSpace domain object.
     *
     * @param greenSpaceDto The GreenSpaceDto object to be converted.
     * @return The corresponding GreenSpace domain object.
     */
    public static GreenSpace toDomain(GreenSpaceDto greenSpaceDto) {
        return new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getType(), greenSpaceDto.getArea(), greenSpaceDto.getManager(), new Address(greenSpaceDto.getAddress(), greenSpaceDto.getCity(), greenSpaceDto.getZipCode()));
    }

    /**
     * Converts a list of GreenSpace domain objects to a list of GreenSpaceDto objects.
     *
     * @param greenSpaceList The list of GreenSpace domain objects to be converted.
     * @return The corresponding list of GreenSpaceDto objects.
     */
    public static List<GreenSpaceDto> toDtoList(List<GreenSpace> greenSpaceList) {
        List<GreenSpaceDto> r = new ArrayList<>();
        for (GreenSpace e : greenSpaceList) {
            r.add(new GreenSpaceDto(e.getName(), e.getType(), e.getArea(), e.getManager(), e.getAddress().getAddress(), e.getAddress().getCity(), e.getAddress().getZipCode()));
        }
        return r;
    }

    /**
     * Converts a list of GreenSpaceDto objects to a list of GreenSpace domain objects.
     *
     * @param greenSpaceListDto The list of GreenSpaceDto objects to be converted.
     * @return The corresponding list of GreenSpace domain objects.
     */
    public static List<GreenSpace> toDomainList(List<GreenSpaceDto> greenSpaceListDto) {
        List<GreenSpace> r = new ArrayList<>();
        for (GreenSpaceDto e : greenSpaceListDto) {
            r.add(new GreenSpace(e.getName(), e.getType(), e.getArea(), e.getManager(), new Address(e.getAddress(), e.getCity(), e.getZipCode())));
        }
        return r;
    }
}
