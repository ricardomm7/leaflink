package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.mappers.JobMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The JobRepository class is responsible for managing the collection of Job objects.
 * It provides methods for creating, retrieving, and checking for duplicate jobs.
 */
public class JobRepository implements Serializable {
    private final List<Job> jobList;

    /**
     * Constructs a new JobRepository object and initializes the job list.
     */
    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * Creates a new Job based on the provided JobDto.
     *
     * @param dto the JobDto containing the job data
     */
    public void createJob(JobDto dto) {
        Job j = JobMapper.toDomain(dto);
        if (checkForDuplicates(j)) {
            addJob(j);
        } else {
            throw new IllegalArgumentException("There is already a job with that name.");
        }
    }

    /**
     * Checks if a Job with the same title already exists in the repository.
     *
     * @param j the Job object to check for duplicates
     * @return true if no duplicate is found, false otherwise
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
     * Adds a Job to the repository.
     *
     * @param job the Job object to be added
     */
    private void addJob(Job job) {
        jobList.add(job);
    }

    /**
     * Gets the list of JobDto objects.
     *
     * @return the list of JobDto objects
     */
    public List<JobDto> getJobList() {
        return JobMapper.toDtoList(jobList);
    }

    /**
     * Remove job.
     *
     * @param index the index
     */
    public void removeJob(int index) {
        jobList.remove(index);
    }
}
