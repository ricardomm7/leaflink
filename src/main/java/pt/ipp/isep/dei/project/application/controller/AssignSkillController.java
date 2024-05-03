package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;


import java.util.List;


public class AssignSkillController {
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Construvts a new AssignSkillController object.
     * Initializes the CollaboratorRepository and JobRepository instances.
     */
    public AssignSkillController() {
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillRepository = repositories.getSkillRepository();
    }



    /**
     * Retrieves the list of available collaborators
     * @return the list of available collaborators
     */
    public List<Collaborator> getCollaborator() {
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Retrieves the list of available skills
     * @return the list of available skills
     */
    public List<Skill> getSkill() {
        return skillRepository.getSkillList();
    }

}
