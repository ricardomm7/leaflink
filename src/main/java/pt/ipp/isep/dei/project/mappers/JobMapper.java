package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;


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
        JobDto jb = new JobDto(job.getTitle());
        return jb;
    }

    /**
     * To domain job.
     *
     * @param jobdto the jobdto
     * @return the job
     */
    public static Job toDomain(JobDto jobdto) {
        Job jb = new Job(jobdto.getTitle());
        return jb;
    }
}
