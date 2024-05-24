package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * The Job class represents a job position within an organization.
 * It holds information about the job title.
 */
public class Job implements Serializable {
    private String title;

    /**
     * Constructs a new Job object with the provided title.
     *
     * @param title the title of the job
     */
    public Job(String title) {
        setTitle(title);
    }

    /**
     * Sets the title of the job.
     *
     * @param title the title of the job
     * @throws IllegalArgumentException if the title is empty, consists only of whitespace, or contains special characters
     */
    public void setTitle(String title) {
        if (!verifyFilled(title) && verifySpecialCharacters(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("The job title is invalid!");
        }
    }

    /**
     * Retrieves the title of the job.
     *
     * @return the title of the job
     */
    public String getTitle() {
        return title;
    }

    /**
     * Verifies if the title contains only alphanumeric characters and spaces.
     *
     * @param title the title to verify
     * @return true if the title contains only alphanumeric characters and spaces, false otherwise
     */
    private boolean verifySpecialCharacters(String title) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, title);
    }

    /**
     * Verifies if the title is filled.
     *
     * @param title the title to verify
     * @return true if the title is not empty or consists only of whitespace, false otherwise
     */
    private boolean verifyFilled(String title) {
        return title.trim().isEmpty();
    }
}
