package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.mappers.SkillMapper;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;

import java.util.List;

/**
 * The AssignSkillController class handles the logic for assigning skills to collaborators.
 * It interacts with the CollaboratorRepository and SkillRepository to retrieve collaborator and skill lists, and updates the collaborator repository after assigning a skill.
 */
public class AssignSkillController {
    private final Repositories repositories;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a new AssignSkillController object.
     * Initializes the CollaboratorRepository and SkillRepository instances.
     */
    public AssignSkillController() {
        repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillRepository = repositories.getSkillRepository();
    }

    /**
     * retrieves the list of collaborators
     *
     * @return the list of collaborators
     */
    public List<CollaboratorDto> getCollaboratorDtoList() {
        return collaboratorRepository.getCollaboratorList();
    }


    /**
     * Retrieves the list of skillsDto
     *
     * @return the list of skillsDto
     */
    public List<SkillDto> getSkillDtoList() {
        return skillRepository.getSkillDtoList();
    }


    /**
     * Assigns one or more skills to a collaborator.
     *
     * @param collaborator The collaborator to whom the skill(s) will be assigned.
     * @param skills       The skills to be assigned.
     */
    public void assignSkills(CollaboratorDto collaborator, List<SkillDto> skills) {
        Collaborator colb = CollaboratorMapper.toDomain(collaborator);
        List<Skill> skillsList = SkillMapper.listToDomain(skills);
        collaboratorRepository.assignSkills(colb, skillsList);
        collaboratorRepository.updateCollaborator(colb); // Update the collaborator in the repository
    }


}