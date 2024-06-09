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

    /**
     * Returns a string representation of the SkillDto object.
     *
     * @return A string representation of the SkillDto object.
     */
    @Override
    public String toString() {
        return "Skill designation= " + designation;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDto skillDto = (SkillDto) o;
        return (designation.equals(skillDto.designation));
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(designation);
    }
}
