package pt.ipp.isep.dei.project.dto;


import java.io.Serializable;

/**
 * The type Job dto.
 */
public class JobDto implements Serializable {
    private String title;

    /**
     * Instantiates a new Job dto.
     *
     * @param title the title
     */
    public JobDto(String title) {
        this.title = title;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

}
