package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.Date;
import java.util.List;

public class RegisterCollaboratorController {
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;


    public RegisterCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        jobRepository = repositories.getJobRepository();
    }

    public void createCLB(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
        collaboratorRepository.create(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
    }

    public List<Job> getJobs() {
        return jobRepository.getJobList();
    }
}
