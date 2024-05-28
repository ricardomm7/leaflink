package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * The Skill class represents a skill with a designation.
 */
public class Skill implements Serializable {

    // The designation of the skill
    private String designation;

    /**
     * Constructs a skill with the given designation.
     *
     * @param designation The designation of the skill.
     */
    public Skill(String designation) {
        setDesignation(designation);
    }

    /**
     * Sets the designation of the skill.
     *
     * @param designation The designation of the skill.
     * @throws IllegalArgumentException If the designation is invalid.
     */
    public void setDesignation(String designation) {
        if (!verifyFilled(designation) && verifySpecialCharacters(designation)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("The skill designation is invalid!");
        }
    }

    /**
     * Verifies if the given designation contains only alphanumeric characters and spaces.
     *
     * @param designation The designation to verify.
     * @return True if the designation contains only alphanumeric characters and spaces, false otherwise.
     */
    private boolean verifySpecialCharacters(String designation) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, designation);
    }

    /**
     * Verifies if the given designation is empty or consists only of whitespace characters.
     *
     * @param designation The designation to verify.
     * @return True if the designation is empty or consists only of whitespace characters, false otherwise.
     */
    private boolean verifyFilled(String designation) {
        return designation.trim().isEmpty();
    }

    /**
     * Gets the designation of the skill.
     *
     * @return The designation of the skill.
     */
    public String getDesignation() {
        return designation;
    }
}
