package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.SkillMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The SkillRepository class is responsible for managing the collection of Skill objects.
 * It provides methods for creating, retrieving, and checking for duplicate skills.
 */
public class SkillRepository implements Serializable {

    private final List<Skill> skillList;

    /**
     * Constructs a new SkillRepository object and initializes the skill list.
     */
    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    /**
     * Creates a new Skill based on the provided SkillDto.
     *
     * @param dto the SkillDto containing the skill data
     */
    public void createSkill(SkillDto dto) {
        Skill skill = SkillMapper.toDomain(dto);
        if (checkForDuplicates(skill)) {
            addSkill(skill);
        } else {
            throw new IllegalArgumentException("There is already a skill with that name.");
        }
    }

    /**
     * Checks if a Skill with the same designation already exists in the repository.
     *
     * @param skill the Skill object to check for duplicates
     * @return true if no duplicate is found, false otherwise
     */
    private boolean checkForDuplicates(Skill skill) {
        for (Skill x : skillList) {
            if (x.getDesignation().equalsIgnoreCase(skill.getDesignation())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a Skill to the repository.
     *
     * @param skill the Skill object to be added
     */
    private void addSkill(Skill skill) {
        skillList.add(skill);
    }

    /**
     * Gets the list of SkillDto objects.
     *
     * @return the list of SkillDto objects
     */
    public List<SkillDto> getSkillDtoList() {
        return SkillMapper.ListToDto(skillList);
    }

    /**
     * Remove skill.
     *
     * @param index the index
     */
    public void removeSkill(int index) {
        skillList.remove(index);
    }

    /**
     * Gets skill list.
     *
     * @return the skill list
     */
    public List<Skill> getSkillList() {
        return new ArrayList<>(skillList);
    }

}
