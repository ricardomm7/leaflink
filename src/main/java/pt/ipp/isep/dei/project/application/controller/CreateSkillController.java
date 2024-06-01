package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;

import java.util.List;

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
     * Creates a new skill with the provided designation.
     *
     * @param designation the designation of the skill to be created
     */
    public void createSkill(String designation) {
        skillRepository.createSkill(new SkillDto(designation));
    }

    /**
     * Gets the list of skill DTOs.
     *
     * @return the list of SkillDto objects
     */
    public List<SkillDto> getSkillsDto() {
        return skillRepository.getSkillDtoList();
    }

    /**
     * Removes a skill at the specified index.
     *
     * @param index the index of the skill to be removed
     */
    public void removeSkill(int index) {
        skillRepository.removeSkill(index);
    }
}
