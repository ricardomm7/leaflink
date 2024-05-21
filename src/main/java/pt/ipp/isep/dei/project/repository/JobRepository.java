package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.mappers.JobMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The JobRepository class manages the storage and retrieval of job positions within the application.
 */
public class JobRepository {
    private final List<Job> jobList;

    /**
     * Constructs a new JobRepository object with an empty list of jobs.
     */
    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * Creates a new job with the provided dto and adds it to the repository if it does not already exist.
     *
     * @param j the dto of the job to create
     */
    public void createJob(Job j) {
        if (checkForDuplicates(j)) {
            addJob(j);
        } else {
            throw new IllegalArgumentException("There is already a job with that name.");
        }
    }

    /**
     * Checks if a job with the same title already exists in the repository.
     *
     * @param j the job to check for duplicates
     * @return true if no job with the same title exists, false otherwise
     */
    private boolean checkForDuplicates(Job j) {
        for (Job x : jobList) {
            if (x.getTitle().equalsIgnoreCase(j.getTitle())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a job to the repository.
     *
     * @param job the job to add
     */
    private void addJob(Job job) {
        jobList.add(job);
    }

    /**
     * Retrieves a copy of the list of jobs stored in the repository.
     *
     * @return a list of jobs
     */
    public List<JobDto> getJobList() {
        List<JobDto> u = new ArrayList<>();
        for (Job j : jobList) {
            JobDto k = JobMapper.toDto(j);
            u.add(k);
        }
        return u;
    }
}

