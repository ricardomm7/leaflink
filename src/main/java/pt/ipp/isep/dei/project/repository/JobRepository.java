package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {
    private final List<Job> jobList;

    public JobRepository() {
        jobList = new ArrayList<>();
    }

    public void createJob(String title) {
        Job j = new Job(title);
        if (checkForDuplicates(j)) {
            addJob(j);
        }
    }

    private boolean checkForDuplicates(Job j) {
        for (Job x : jobList) {
            if (x.getTitle().equalsIgnoreCase(j.getTitle())) {
                return false;
            }
        }
        return true;
    }

    private void addJob(Job job) {
        jobList.add(job);
    }

    public List<Job> getJobList() {
        return new ArrayList<>(jobList);
    }
}
