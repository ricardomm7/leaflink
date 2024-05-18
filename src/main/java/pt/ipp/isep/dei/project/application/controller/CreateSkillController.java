package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;

/**
 * This class represents the controller responsible for creating a new skill.
 */
public class CreateSkillController {

    private final SkillRepository skillRepository;

    /**
     * Constructs a new CreateSkillController object.
     * Initializes the SkillRepository using the Repositories singleton instance.
     */
    public CreateSkillController() {
        Repositories repositories = Repositories.getInstance();
        skillRepository = repositories.getSkillRepository();
    }

    /**
     * Creates a new skill with the specified designation.
     *
     * @param designation the designation of the skill to be created.
     */
    public void createSkill(String designation) {
        skillRepository.createSkill(designation);
    }
}
