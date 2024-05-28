
package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The ToDoEntryMapper class is responsible for mapping between the ToDoEntry domain object and the ToDoEntryDto data transfer object.
 * It provides methods to convert a ToDoEntry object to a ToDoEntryDto object and vice versa, as well as a method to convert
 * a list of ToDoEntry objects to a list of ToDoEntryDto objects.
 */
public class ToDoEntryMapper implements Serializable {

    /**
     * Converts a ToDoEntry domain object to a ToDoEntryDto data transfer object.
     *
     * @param toDoEntry the ToDoEntry domain object to be converted
     * @return the corresponding ToDoEntryDto data transfer object
     */
    public static ToDoEntryDto toDto(ToDoEntry toDoEntry) {
        GreenSpaceDto greenSpaceDto = GreenSpaceMapper.toDto(toDoEntry.getGreenSpace());
        return new ToDoEntryDto(
                toDoEntry.getTitle(),
                toDoEntry.getDescription(),
                toDoEntry.getDuration(),
                toDoEntry.getUrgencyStatus(),
                greenSpaceDto
        );
    }

    /**
     * Converts a ToDoEntryDto data transfer object to a ToDoEntry domain object.
     *
     * @param toDoEntryDto the ToDoEntryDto data transfer object to be converted
     * @return the corresponding ToDoEntry domain object
     */
    public static ToDoEntry toDomain(ToDoEntryDto toDoEntryDto) {
        GreenSpace greenSpace = GreenSpaceMapper.toDomain(toDoEntryDto.getGreenSpace());
        return new ToDoEntry(
                toDoEntryDto.getTitle(),
                toDoEntryDto.getDescription(),
                toDoEntryDto.getDuration(),
                toDoEntryDto.getUrgencyStatus(),
                greenSpace
        );
    }

    /**
     * Converts a list of ToDoEntry domain objects to a list of ToDoEntryDto data transfer objects.
     *
     * @param entries the list of ToDoEntry domain objects to be converted
     * @return the corresponding list of ToDoEntryDto data transfer objects
     */
    public static List<ToDoEntryDto> toDtoList(List<ToDoEntry> entries) {
        List<ToDoEntryDto> dtos = new ArrayList<>();
        for (ToDoEntry entry : entries) {
            dtos.add(toDto(entry));
        }
        return dtos;
    }
}
