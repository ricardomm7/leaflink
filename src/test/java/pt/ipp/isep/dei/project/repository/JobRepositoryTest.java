package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.mappers.JobMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {

    private JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        jobRepository = new JobRepository();
    }

    @Test
    void testCreateJob() {
        String title = "Software Developer";
        JobDto j1 = new JobDto(title);

        jobRepository.createJob(j1);

        List<JobDto> jobList = jobRepository.getJobList();

        assertFalse(jobList.isEmpty());
        assertEquals(title, jobList.get(0).getTitle());
    }

    @Test
    void testCreateDuplicateJob() {
        String title = "Software Developer";
        JobDto j1 = new JobDto(title);

        // Create the job for the first time
        jobRepository.createJob(j1);

        // Attempt to create the same job again should throw an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            jobRepository.createJob(j1);
        });

        assertEquals("There is already a job with that name.", thrown.getMessage());
    }

    @Test
    void testGetJobList() {
        String title1 = "Software Developer";
        String title2 = "Data Analyst";

        JobDto j1 = new JobDto(title1);
        JobDto j2 = new JobDto(title2);

        jobRepository.createJob(j1);
        jobRepository.createJob(j2);

        List<JobDto> jobList = jobRepository.getJobList();

        assertEquals(2, jobList.size());
        assertEquals(title1, jobList.get(0).getTitle());
        assertEquals(title2, jobList.get(1).getTitle());
    }

    @Test
    void testRemoveJob() {
        String title1 = "Software Developer";
        String title2 = "Data Analyst";

        JobDto j1 = new JobDto(title1);
        JobDto j2 = new JobDto(title2);

        jobRepository.createJob(j1);
        jobRepository.createJob(j2);

        int initialSize = jobRepository.getJobList().size();
        jobRepository.removeJob(0);

        List<JobDto> jobList = jobRepository.getJobList();

        assertEquals(initialSize - 1, jobList.size());
        assertEquals(title2, jobList.get(0).getTitle());
    }

    @Test
    void testRemoveJob_InvalidIndex() {
        JobDto j1 = new JobDto("Software Developer");

        jobRepository.createJob(j1);

        // Attempt to remove a job with an invalid index should throw IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            jobRepository.removeJob(1);
        });
    }
}
