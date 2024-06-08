package pt.ipp.isep.dei.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddAgendaEntryControllerTest {

    private AddAgendaEntryController controller;
    private RegisterToDoEntryController controller1;

    @BeforeEach
    void setUp() {
        controller = new AddAgendaEntryController();
        controller1 = new RegisterToDoEntryController();
    }

    @Test
    void testGetToDoEntry() {
        // Arrange
        // Criar uma lista de To Do Entry Dtos para simular a lista retornada pelo repositório
        List<ToDoEntryDto> expectedDtoList = new ArrayList<>();
        ToDoEntryDto a1 = new ToDoEntryDto("Title1", "Description1", 1, UrgencyStatus.HIGH, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"));
        ToDoEntryDto a2 = new ToDoEntryDto("Title2", "Description2", 2, UrgencyStatus.MEDIUM, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"));

        expectedDtoList.add(a1);
        expectedDtoList.add(a2);

        controller1.createNewToDoEntry("Title1", "Description1", 1, UrgencyStatus.HIGH, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"));
        controller1.createNewToDoEntry("Title2", "Description2", 2, UrgencyStatus.MEDIUM, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"));
        // Act
        List<ToDoEntryDto> result = controller.getToDoEntry();

        // Assert
        assertEquals(expectedDtoList, result);
    }

    @Test
    void testGetAgendaEntries() {
        // Arrange
        UserSession userSession = new UserSession(new pt.isep.lei.esoft.auth.UserSession(new User(new Email("a@a.com"), new Password("abcd"), "a")));
        // Criar uma lista de Agenda Entry Dtos para simular a lista retornada pelo repositório
        List<AgendaEntryDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new AgendaEntryDto("Title1", "Description1", 1, UrgencyStatus.HIGH, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"), LocalDate.now(), ProgressStatus.PLANNED));
        expectedDtoList.add(new AgendaEntryDto("Title2", "Description2", 2, UrgencyStatus.MEDIUM, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"), LocalDate.now(), ProgressStatus.PENDING));

        controller.addAgendaEntry(new AgendaEntryDto("Title1", "Description1", 1, UrgencyStatus.HIGH, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"), LocalDate.now(), ProgressStatus.PLANNED));
        controller.addAgendaEntry(new AgendaEntryDto("Title2", "Description2", 2, UrgencyStatus.MEDIUM, new GreenSpaceDto("s", GreenSpaceType.GARDEN, 5432, "a@a.com", "4efs", "gfer", "4444-321"), LocalDate.now(), ProgressStatus.PENDING));

        // Act
        List<AgendaEntryDto> result = controller.getAgendaEntries(userSession);

        // Assert
        assertEquals(expectedDtoList.size(), result.size());
    }
}
