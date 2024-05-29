package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.List;

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
     * Creates a new job with the provided dto.
     *
     * @param dto the dto of the job to be created
     */
    public void createJob(String designation) {
        jobRepository.createJob(new JobDto(designation));
    }

    /**
     * Gets job list.
     *
     * @return the job list
     */
    public List<JobDto> getJobList() {
        return jobRepository.getJobList();
    }

    /**
     * Remove job.
     *
     * @param index the index
     */
    public void removeJob(int index) {
        jobRepository.removeJob(index);
    }
}

