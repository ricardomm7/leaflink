package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The CollaboratorRepository class is responsible for managing the collection of collaborators.
 * It provides methods for creating, retrieving, updating, and assigning skills to collaborators.
 */
public class CollaboratorRepository implements Serializable {
    private final List<Collaborator> collaboratorList;

    /**
     * Constructs a new CollaboratorRepository object and initializes the collaborator list.
     */
    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

    /**
     * Creates a new collaborator based on the provided CollaboratorDto.
     *
     * @param dto the CollaboratorDto containing the collaborator data
     */
    public void create(CollaboratorDto dto) {
        Collaborator c = CollaboratorMapper.toDomain(dto);
        try {
            if (checkForDuplicates(c)) {
                addCollaborator(c);
            } else {
                throw new IllegalArgumentException("There is already a registered employee with the same attributes.");
            }
        } catch (Exception e) {
            ShowError.showAlert("Collaborator", e.getMessage(), "Duplicate");
        }
    }


    /**
     * Checks if a collaborator with the same taxpayer number already exists in the repository.
     *
     * @param j the Collaborator object to check for duplicates
     * @return true if no duplicate is found, false otherwise
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
     * Remove.
     *
     * @param index the index
     */
    public void remove(int index) {
        collaboratorList.remove(index);
    }

    /**
     * Gets the list of collaborators as CollaboratorDto objects.
     *
     * @return the list of collaborators as CollaboratorDto objects
     */
    public List<CollaboratorDto> getCollaboratorDtoList() {
       return CollaboratorMapper.toDtoList(collaboratorList);
    }

    public List<Collaborator> getCollaboratorList() {
        return new ArrayList<>(collaboratorList);
    }

    /**
     * Adds a collaborator to the repository.
     *
     * @param collab the Collaborator object to be added
     */
    public void addCollaborator(Collaborator collab) {
        collaboratorList.add(collab);
    }

    /**
     * Assigns a list of skills to a collaborator.
     *
     * @param collaborator the Collaborator object to assign skills to
     * @param skills       the list of Skill objects to be assigned
     */
    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaborator.assignSkills(skills);
    }

    /**
     * Updates an existing collaborator in the repository.
     *
     * @param collaborator the updated Collaborator object
     */
    public void updateCollaborator(Collaborator collaborator) {
        // Find the collaborator in the list and update it
        for (int i = 0; i < collaboratorList.size(); i++) {
            if (collaboratorList.get(i).getTaxpayerNumber() == collaborator.getTaxpayerNumber()) {
                collaboratorList.set(i, collaborator);
                return;
            }
        }
    }
}
