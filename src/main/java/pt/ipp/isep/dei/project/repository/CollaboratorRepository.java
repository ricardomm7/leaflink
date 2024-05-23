package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;

import java.util.ArrayList;
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
     * Add the collaborator created, with all attributes, to the collaborators list if's not a duplicate..
     *
     * @param c the collaborator created.
     */
    public void create(Collaborator c) {
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
    public List<CollaboratorDto> getCollaboratorList() {
        return CollaboratorMapper.toDtoList(collaboratorList);
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
        collaborator.assignSkills(skills);
    }
}

