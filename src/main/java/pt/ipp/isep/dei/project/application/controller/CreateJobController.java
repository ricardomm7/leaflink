package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

/**
 * The CreateJobController class manages the creation of job positions within the application.
 * It interacts with the JobRepository to store job-related information.
 */
public class CreateJobController {
    private final JobRepository jobRepository;

    /**
     * Constructs a new CreateJobController object.
     * Initializes the JobRepository instance.
     */
    public CreateJobController() {
        Repositories repositories = Repositories.getInstance();
        jobRepository = repositories.getJobRepository();
    }

    /**
     * Creates a new job with the provided title.
     *
     * @param title the title of the job to be created
     */
    public void createJob(String title) {
        jobRepository.createJob(title);
    }
}

