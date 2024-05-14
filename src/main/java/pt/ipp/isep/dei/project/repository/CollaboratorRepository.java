package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The CollaboratorRepository class manages the storage and retrieval of collaborators within the application.
 */
public class CollaboratorRepository {
    private final List<Collaborator> collaboratorList;

    /**
     * Constructs a new CollaboratorRepository object with an empty list of collaborators.
     */
    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

    /**
     * Creates a new collaborator with the provided information and adds it to the repository if it does not already exist.
     *
     * @param name                 the name of the collaborator
     * @param birthdate            the birthdate of the collaborator
     * @param contactMobile        the mobile contact number of the collaborator
     * @param taxpayerNumber       the taxpayer number of the collaborator
     * @param email                the email address of the collaborator
     * @param address              the address of the collaborator
     * @param zipCode              the ZIP code of the collaborator's address
     * @param city                 the city of the collaborator's address
     * @param documentType         the document type of the collaborator's identification
     * @param identificationNumber the identification number of the collaborator
     * @param admissionDate        the date of admission of the collaborator
     * @param job                  the job of the collaborator
     */
    public void create(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
        Collaborator c = new Collaborator(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
        if (checkForDuplicates(c)) {
            addCollaborator(c);
        }
    }

    /**
     * Checks if a collaborator with the same taxpayer number already exists in the repository.
     *
     * @param j the collaborator to check for duplicates
     * @return true if no collaborator with the same taxpayer number exists, false otherwise
     */
    private boolean checkForDuplicates(Collaborator j) {
        for (Collaborator x : collaboratorList) {
            if (x.getTaxpayerNumber() == j.getTaxpayerNumber()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the list of collaborators stored in the repository.
     *
     * @return the list of collaborators
     */
    public List<Collaborator> getCollaboratorList() {
        return new ArrayList<>(collaboratorList);
    }

    /**
     * Adds a collaborator to the repository.
     *
     * @param collab the collaborator to add
     */
    public void addCollaborator(Collaborator collab) {
        collaboratorList.add(collab);
    }

    /**
     * Assigns one or more skills to a collaborator.
     *
     * @param collaborator The collaborator to whom the skill(s) will be assigned.
     * @param skills       The skills to be assigned.
     */
    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaborator.assignSkills(skills.toArray(new Skill[0]));
    }
}

