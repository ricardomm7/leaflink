package pt.ipp.isep.dei.project.domain;

import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * The Job class represents a job with a title.
 * It ensures that the title is valid according to specified rules.
 */
public class Job implements Serializable {

    private String title;

    /**
     * Constructs a Job with the specified title.
     *
     * @param title the title of the job
     * @throws IllegalArgumentException if the title is invalid
     */
    public Job(String title) {
        try {
            setTitle(title);
        }catch (Exception e){
            ShowError.showAlert("Job", e.getMessage(), null);
        }
    }

    /**
     * Sets the title of the job.
     *
     * @param title the new title of the job
     * @throws IllegalArgumentException if the title is invalid
     */
    public void setTitle(String title) {
        if (!verifyFilled(title) && verifySpecialCharacters(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("The job title is invalid!");
        }
    }

    /**
     * Returns the title of the job.
     *
     * @return the title of the job
     */
    public String getTitle() {
        return title;
    }

    /**
     * Verifies if the title contains only allowed characters.
     *
     * @param title the title to be verified
     * @return true if the title contains only allowed characters, false otherwise
     */
    private boolean verifySpecialCharacters(String title) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, title);
    }

    /**
     * Verifies if the title is filled (not empty or only whitespace).
     *
     * @param title the title to be verified
     * @return true if the title is empty or only whitespace, false otherwise
     */
    private boolean verifyFilled(String title) {
        return title.trim().isEmpty();
    }
}
