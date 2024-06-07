package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.SkillDto;

import static org.junit.jupiter.api.Assertions.*;

class SkillRepositoryTest {

    private SkillRepository skillRepository;

    @BeforeEach
    void setUp() {
        skillRepository = new SkillRepository();
    }

    @Test
    void createSkill_Successfully() {
        // Arrange
        SkillDto dto = new SkillDto("Java Programming");

        // Act
        skillRepository.createSkill(dto);

        // Assert
        assertEquals(1, skillRepository.getSkillList().size());
    }

    @Test
    void createSkill_WithDuplicateName_ShouldThrowException() {
        // Arrange
        SkillDto dto1 = new SkillDto("Java Programming");
        SkillDto dto2 = new SkillDto("Java Programming");
        skillRepository.createSkill(dto1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> skillRepository.createSkill(dto2));
        assertEquals("There is already a skill with that name.", exception.getMessage());
    }

    @Test
    void removeSkill_Successfully() {
        // Arrange
        SkillDto dto = new SkillDto("Java Programming");
        skillRepository.createSkill(dto);
        int initialSize = skillRepository.getSkillList().size();

        // Act
        skillRepository.removeSkill(0);

        // Assert
        assertEquals(initialSize - 1, skillRepository.getSkillList().size());
    }
}
