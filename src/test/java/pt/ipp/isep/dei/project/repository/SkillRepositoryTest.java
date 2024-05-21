package pt.ipp.isep.dei.project.repository;

import org.junit.Test;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.SkillMapper;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillRepositoryTest {

    @Test
    public void testCreateSkill() {
        SkillRepository repository = new SkillRepository();
        String designation = "Programming";

        repository.createSkill(SkillMapper.toDomain(new SkillDto(designation)));
        List<Skill> skillList = repository.getSkillList();

        assertEquals(1, skillList.size());
        assertEquals(designation, skillList.get(0).getDesignation());
    }

    @Test
    public void testCreateDuplicateSkill() {
        SkillRepository repository = new SkillRepository();
        String designation = "Programming";

        repository.createSkill(SkillMapper.toDomain(new SkillDto(designation)));

        assertThrows(IllegalArgumentException.class, () -> repository.createSkill(SkillMapper.toDomain(new SkillDto("Programming"))));
    }

    @Test
    public void testGetSkillList() {
        SkillRepository repository = new SkillRepository();
        String designation1 = "Programming";
        String designation2 = "Project Management";

        repository.createSkill(SkillMapper.toDomain(new SkillDto(designation1)));
        repository.createSkill(SkillMapper.toDomain(new SkillDto(designation2)));
        List<Skill> skillList = repository.getSkillList();

        assertEquals(2, skillList.size());
        assertEquals(designation1, skillList.get(0).getDesignation());
        assertEquals(designation2, skillList.get(1).getDesignation());
    }
}
