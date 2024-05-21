package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Job mapper.
 */
public class JobMapper {

    /**
     * To dto job dto.
     *
     * @param job the job
     * @return the job dto
     */
    public static JobDto toDto(Job job) {
        return new JobDto(job.getTitle());
    }

    /**
     * To domain job.
     *
     * @param jobdto the jobdto
     * @return the job
     */
    public static Job toDomain(JobDto jobdto) {
        return new Job(jobdto.getTitle());
    }

    /**
     * List to dto list.
     *
     * @param j the j
     * @return the list
     */
    public static List<JobDto> listToDto(List<Job> j) {
        List<JobDto> u = new ArrayList<>();
        for (Job l : j) {
            JobDto k = JobMapper.toDto(l);
            u.add(k);
        }
        return u;
    }
}
