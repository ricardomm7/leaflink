package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.util.List;

/**
 * The RegisterCollaboratorController class manages the registration of collaborators within the application.
 * It interacts with the CollaboratorRepository and JobRepository to store and retrieve collaborator and job-related information.
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
     * Create clb from a collaborator DTO.
     *
     * @param collDto the collaborator dto
     */
    public void createCLB(CollaboratorDto collDto) {
        collaboratorRepository.create(collDto);

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