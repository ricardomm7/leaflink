package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;

import java.time.LocalDate;
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
     * Creates a new collaborator with the provided details.
     *
     * @param name                 the name of the collaborator
     * @param birthdate            the birthdate of the collaborator
     * @param contactMobile        the mobile contact number of the collaborator
     * @param taxpayerNumber       the taxpayer number of the collaborator
     * @param email                the email address of the collaborator
     * @param address              the address of the collaborator
     * @param zipCode              the zip code of the collaborator's address
     * @param city                 the city of the collaborator's address
     * @param documentType         the document type of the collaborator
     * @param identificationNumber the identification number of the collaborator
     * @param admissionDate        the admission date of the collaborator
     * @param job                  the job position of the collaborator
     */
    public void createCLB(String name, LocalDate birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate, Job job) {
        collaboratorRepository.create(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
    }

    /**
     * Retrieves the list of available job positions.
     *
     * @return the list of available job positions
     */
    public List<Job> getJobs() {
        return jobRepository.getJobList();
    }
}