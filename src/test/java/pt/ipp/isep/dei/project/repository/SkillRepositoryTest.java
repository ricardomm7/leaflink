package pt.ipp.isep.dei.project.repository;

import org.junit.Test;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillRepositoryTest {

    @Test
    public void testCreateSkill() {
        SkillRepository repository = new SkillRepository();
        String designation = "Programming";

        repository.createSkill(designation);
        List<Skill> skillList = repository.getSkillList();

        assertEquals(1, skillList.size());
        assertEquals(designation, skillList.get(0).getDesignation());
    }

    @Test
    public void testCreateDuplicateSkill() {
        SkillRepository repository = new SkillRepository();
        String designation = "Programming";

        repository.createSkill(designation);

        assertThrows(IllegalArgumentException.class, () -> repository.createSkill("Programming"));
    }

    @Test
    public void testGetSkillList() {
        SkillRepository repository = new SkillRepository();
        String designation1 = "Programming";
        String designation2 = "Project Management";

        repository.createSkill(designation1);
        repository.createSkill(designation2);
        List<Skill> skillList = repository.getSkillList();

        assertEquals(2, skillList.size());
        assertEquals(designation1, skillList.get(0).getDesignation());
        assertEquals(designation2, skillList.get(1).getDesignation());
    }
}
