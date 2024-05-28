
package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;

/**
 * The JobDto class represents a data transfer object for the Job domain object.
 * It encapsulates the data related to a job and provides a method to access the job title.
 */
public class JobDto implements Serializable {
    private final String title;

    /**
     * Constructs a new JobDto object with the provided job title.
     *
     * @param title the title of the job
     */
    public JobDto(String title) {
        this.title = title;
    }

    /**
     * Gets the title of the job.
     *
     * @return the title of the job
     */
    public String getTitle() {
        return title;
    }
}
