package pt.ipp.isep.dei.project.domain;

import java.util.regex.Pattern;

public class Skill {
    private String designation;

    public Skill(String designation) {
        setDesignation(designation);
    }

    public void setDesignation(String designation) {
        if (!verifyFilled(designation) && verifySpecialCharacters(designation)) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("The skill designation is invalid!");
        }
    }

    private boolean verifySpecialCharacters(String title) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, title);
    }

    private boolean verifyFilled(String title) {
        return title.trim().isEmpty();
    }

    public String getDesignation() {
        return designation;
    }
}
