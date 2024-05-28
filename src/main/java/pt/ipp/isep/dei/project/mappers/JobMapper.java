
package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The JobMapper class is responsible for mapping between the Job domain object and the JobDto data transfer object.
 */
public class JobMapper implements Serializable {

    /**
     * Converts a Job domain object to a JobDto data transfer object.
     *
     * @param job the Job domain object
     * @return the JobDto data transfer object
     */
    public static JobDto toDto(Job job) {
        return new JobDto(job.getTitle());
    }

    /**
     * Converts a JobDto data transfer object to a Job domain object.
     *
     * @param jobDto the JobDto data transfer object
     * @return the Job domain object
     */
    public static Job toDomain(JobDto jobDto) {
        return new Job(jobDto.getTitle());
    }

    /**
     * Converts a list of JobDto data transfer objects to a list of Job domain objects.
     *
     * @param jobDtos the list of JobDto data transfer objects
     * @return the list of Job domain objects
     */
    public static List<Job> toDomainList(List<JobDto> jobDtos) {
        List<Job> jobs = new ArrayList<>();
        for (JobDto jobDto : jobDtos) {
            jobs.add(toDomain(jobDto));
        }
        return jobs;
    }

    /**
     * Converts a list of Job domain objects to a list of JobDto data transfer objects.
     *
     * @param jobs the list of Job domain objects
     * @return the list of JobDto data transfer objects
     */
    public static List<JobDto> toDtoList(List<Job> jobs) {
        List<JobDto> jobDtos = new ArrayList<>();
        for (Job job : jobs) {
            jobDtos.add(toDto(job));
        }
        return jobDtos;
    }
}
