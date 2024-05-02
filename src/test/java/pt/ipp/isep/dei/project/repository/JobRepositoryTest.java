package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Job;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {

    @Test
    void testCreateJob() {
        JobRepository jobRepository = new JobRepository();
        String title = "Software Developer";
        jobRepository.createJob(title);

        List<Job> jobList = jobRepository.getJobList();

        assertFalse(jobList.isEmpty());
        assertEquals(title, jobList.get(0).getTitle());
    }

    @Test
    void testCreateDuplicateJob() {
        JobRepository jobRepository = new JobRepository();
        String title = "Software Developer";
        jobRepository.createJob(title);

        // Trying to add duplicate job
        jobRepository.createJob(title);

        List<Job> jobList = jobRepository.getJobList();

        assertEquals(1, jobList.size());
    }

    @Test
    void testGetJobList() {
        JobRepository jobRepository = new JobRepository();
        String title1 = "Software Developer";
        String title2 = "Data Analyst";
        jobRepository.createJob(title1);
        jobRepository.createJob(title2);

        List<Job> jobList = jobRepository.getJobList();

        assertEquals(2, jobList.size());
        assertEquals(title1, jobList.get(0).getTitle());
        assertEquals(title2, jobList.get(1).getTitle());
    }
}
