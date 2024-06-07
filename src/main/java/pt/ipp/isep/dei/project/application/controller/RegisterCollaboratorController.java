package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.JobRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.ui.ShowError;

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
     * Creates a new collaborator with the provided information.
     *
     * @param name                 the name of the collaborator
     * @param birthdate1           the birthdate of the collaborator
     * @param contactMobile        the contact mobile number of the collaborator
     * @param taxpayerNumber       the taxpayer number of the collaborator
     * @param email                the email address of the collaborator
     * @param address              the address of the collaborator
     * @param zipCode              the zip code of the collaborator's address
     * @param city                 the city of the collaborator's address
     * @param documentType         the document type of the collaborator
     * @param identificationNumber the identification number of the collaborator
     * @param admissionDate1       the admission date of the collaborator
     * @param job                  the job position of the collaborator
     */
    public void createCLB(String name, LocalDate birthdate1, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, DocumentType documentType, String identificationNumber, LocalDate admissionDate1, Job job) {
        try {
            collaboratorRepository.create(new CollaboratorDto(name, birthdate1, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate1, job));
        } catch (Exception e) {
            ShowError.showAlert("Collaborator", e.getMessage(), "Duplicate");
        }
    }

    /**
     * Retrieves the list of available job positions.
     *
     * @return the list of available job positions
     */
    public List<JobDto> getJobs() {
        return jobRepository.getJobList();
    }

    /**
     * Gets the list of collaborators.
     *
     * @return the list of collaborators
     */
    public List<CollaboratorDto> getCollaborators() {
        return collaboratorRepository.getCollaboratorDtoList();
    }

    /**
     * Removes a collaborator from the repository.
     *
     * @param index the index of the collaborator to be removed
     */
    public void removeCollab(int index) {
        collaboratorRepository.remove(index);
    }
}
