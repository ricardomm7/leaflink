package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

public class CreateJobController {
    private final JobRepository jobRepository;

    public CreateJobController() {
        Repositories repositories = Repositories.getInstance();
        jobRepository = repositories.getJobRepository();
    }

    public void createJob(String title) {
        jobRepository.createJob(title);
    }

}
