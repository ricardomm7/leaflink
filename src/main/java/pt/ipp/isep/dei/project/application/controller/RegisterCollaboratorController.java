package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.time.LocalDate;
import java.util.List;

/**
 * The RegisterCollaboratorController class handles the logic for registering new collaborators
 * and retrieving the list of available job positions.
 */
public class RegisterCollaboratorController {
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;

    /**
     * Constructs a new RegisterCollaboratorController object.
     * Initializes the CollaboratorRepository and JobRepository instances.
     */
    public RegisterCollaboratorController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        jobRepository = repositories.getJobRepository();
    }

    /**
     * Creates a new collaborator based on the provided CollaboratorDto.
     *
     * @param collDto the CollaboratorDto containing the collaborator data
     */
    public void createCLB(String name, LocalDate birthdate1, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate1, Job job) {
        collaboratorRepository.create(new CollaboratorDto(name, birthdate1, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate1, job));
    }

    /**
     * Retrieves the list of available job positions.
     *
     * @return the list of available job positions
     */
    public List<JobDto> getJobs() {
        return jobRepository.getJobList();
    }
}
