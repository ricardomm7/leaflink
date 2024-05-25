package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.GreenSpace;
import pt.ipp.isep.dei.project.domain.ToDoEntry;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToDoEntryMapper implements Serializable {

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
        public static List<ToDoEntryDto> toDtoList(List<ToDoEntry> entries) {
        List<ToDoEntryDto> dtos = new ArrayList<>();
        for (ToDoEntry entry : entries) {
            dtos.add(toDto(entry));
        }
        return dtos;
    }
}
