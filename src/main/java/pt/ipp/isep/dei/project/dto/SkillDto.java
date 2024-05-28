
package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * The SkillDto class represents a data transfer object for the Skill domain object.
 * It encapsulates the data related to a skill and provides methods to access and manipulate this data.
 */
public class SkillDto implements Serializable {

    private final String designation;

    /**
     * Constructs a new SkillDto object with the provided designation.
     *
     * @param designation the designation of the skill
     */
    public SkillDto(String designation) {
        this.designation = designation;
    }

    /**
     * Gets the designation of the skill.
     *
     * @return the designation of the skill
     */
    public String getDesignation() {
        return designation;
    }

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
}
