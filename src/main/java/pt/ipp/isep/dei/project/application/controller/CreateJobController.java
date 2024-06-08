package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.ui.ShowError;

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
     * Creates a new job with the provided designation.
     *
     * @param designation the designation of the job to be created
     */
    public void createJob(String designation) {
        try {
            jobRepository.createJob(new JobDto(designation));
        } catch (Exception e) {
            ShowError.showAlert("Job", e.getMessage(), "Error");
        }
    }

    /**
     * Gets the list of jobs.
     *
     * @return the list of JobDto objects
     */
    public List<JobDto> getJobList() {
        return jobRepository.getJobList();
    }

    /**
     * Removes a job at the specified index.
     *
     * @param index the index of the job to be removed
     */
    public void removeJob(int index) {
        jobRepository.removeJob(index);
    }
}
