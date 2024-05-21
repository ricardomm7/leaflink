package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.SkillMapper;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;

import java.util.LinkedList;
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
     * Creates a new skill with the specified designation.
     *
     * @param skillDto the designation of the skill to be created.
     */
    public void createSkill(SkillDto skillDto) {
        Skill skill = SkillMapper.toDomain(skillDto);
        skillRepository.createSkill(skill);
    }

    public List<SkillDto> getSkillsDto() {
        List<Skill> skills = skillRepository.getSkillList();
        List<SkillDto> listToReturn = new LinkedList<>();
        for (Skill s : skills) {
            listToReturn.add(SkillMapper.toDto(s));
        }
        return listToReturn;
    }
}
