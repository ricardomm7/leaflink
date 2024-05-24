package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * This class represents a skill.
 */
public class Skill implements Serializable {
    private String designation;

    /**
     * Constructs a new Skill object with the specified designation.
     *
     * @param designation the designation of the skill.
     */
    public Skill(String designation) {
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
