package pt.ipp.isep.dei.project.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkillTest {

    @Test
    public void testValidSkillDesignation() {
        String validDesignation = "Programming";

        Skill skill = new Skill(validDesignation);

        assertEquals(validDesignation, skill.getDesignation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithSpecialCharacters() {
        String invalidDesignation = "Progra**mming";

        new Skill(invalidDesignation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithEmptyString() {
        String invalidDesignation = "";

        new Skill(invalidDesignation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithOnlySpaces() {
        String invalidDesignation = "    ";

        new Skill(invalidDesignation);
    }
}
