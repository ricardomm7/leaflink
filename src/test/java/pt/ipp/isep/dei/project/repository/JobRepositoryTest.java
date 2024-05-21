package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.mappers.JobMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {

    @Test
    void testCreateJob() {
        JobRepository jobRepository = new JobRepository();
        String title = "Software Developer";
        JobDto j1 = new JobDto(title);

        jobRepository.createJob(JobMapper.toDomain(j1));

        List<JobDto> jobList = jobRepository.getJobList();

        assertFalse(jobList.isEmpty());
        assertEquals(title, jobList.get(0).getTitle());
    }

    @Test
    void testCreateDuplicateJob() {
        JobRepository jobRepository = new JobRepository();
        String title = "Software Developer";
        JobDto j1 = new JobDto(title);

        // Criar o trabalho pela primeira vez
        jobRepository.createJob(JobMapper.toDomain(j1));

        // Tentar criar o mesmo trabalho novamente deve lançar uma exceção
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            jobRepository.createJob(JobMapper.toDomain(j1));
        });

        assertNotNull(thrown);
    }

    @Test
    void testGetJobList() {
        JobRepository jobRepository = new JobRepository();
        String title1 = "Software Developer";
        String title2 = "Data Analyst";

        JobDto j1 = new JobDto(title1);
        JobDto j2 = new JobDto(title2);

        jobRepository.createJob(JobMapper.toDomain(j1));
        jobRepository.createJob(JobMapper.toDomain(j2));

        List<JobDto> jobList = jobRepository.getJobList();

        assertEquals(2, jobList.size());
        assertEquals(title1, jobList.get(0).getTitle());
        assertEquals(title2, jobList.get(1).getTitle());
    }
}
