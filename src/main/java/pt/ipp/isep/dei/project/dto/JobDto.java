package pt.ipp.isep.dei.project.dto;


/**
 * The type Job dto.
 */
public class JobDto {
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

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
