package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;


import java.util.List;


public class AssignSkillController {
    private final Repositories repositories;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Construvts a new AssignSkillController object.
     * Initializes the CollaboratorRepository and JobRepository instances.
     */
    public AssignSkillController() {
        repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
        skillRepository = repositories.getSkillRepository();
    }

    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    public List<Skill> getSkillList() {
        return skillRepository.getSkillList();
    }

    public void assignSkill(Collaborator collaborator, Skill skill) {
        collaborator.assignSkills(new Skill[]{skill});
        collaboratorRepository.updateCollaborator(collaborator);
    }

}
