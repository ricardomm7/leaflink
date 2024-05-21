package pt.ipp.isep.dei.project.dto;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The type Skill dto.
 */
public class SkillDto {

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
        return Objects.equals(designation, skillDto.designation);
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
        setDesignation(designation);
    }

    /**
     * Sets the designation of the skill.
     *
     * @param designation the designation to set.
     * @throws IllegalArgumentException if the skill designation is invalid.
     */
    public void setDesignation(String designation) {
        if (!verifyFilled(designation) && verifySpecialCharacters(designation)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("The skill designation is invalid!");
        }
    }

    /**
     * Verifies if the designation contains special characters.
     *
     * @param designation the designation to verify.
     * @return true if the designation contains only letters, numbers, or spaces, false otherwise.
     */
    private boolean verifySpecialCharacters(String designation) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, designation);
    }

    /**
     * Verifies if the designation is filled.
     *
     * @param designation the designation to verify.
     * @return true if the designation is not empty after trimming, false otherwise.
     */
    private boolean verifyFilled(String designation) {
        return designation.trim().isEmpty();
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
