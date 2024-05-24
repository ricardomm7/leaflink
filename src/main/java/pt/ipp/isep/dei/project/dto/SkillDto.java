package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Skill dto.
 */
public class SkillDto implements Serializable {

    private String designation;

    @Override
    public String toString() {
        return "Skill designation= " + designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDto skillDto = (SkillDto) o;
        return (designation.equals(skillDto.designation));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(designation);
    }

    /**
     * Constructs a new Skill object with the specified designation.
     *
     * @param designation the designation of the skill.
     */
    public SkillDto(String designation) {
        this.designation = designation;
    }

    /**
     * Gets the designation of the skill.
     *
     * @return the designation of the skill.
     */
    public String getDesignation() {
        return designation;
    }
}
