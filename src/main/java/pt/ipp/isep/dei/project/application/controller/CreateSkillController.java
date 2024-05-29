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
     * Create skill.
     *
     * @param designation the designation
     */
    public void createSkill(String designation) {
        skillRepository.createSkill(new SkillDto(designation));
    }

    /**
     * Gets skills dto.
     *
     * @return the skills dto
     */
    public List<SkillDto> getSkillsDto() {
        return skillRepository.getSkillDtoList();
    }

    /**
     * Remove skill.
     *
     * @param index the index
     */
    public void removeSkill(int index) {
        skillRepository.removeSkill(index);
    }
}
