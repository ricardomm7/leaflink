package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a repository for storing skills.
 */
public class SkillRepository {

    private final List<Skill> skillList;

    /**
     * Constructs a new SkillRepository object with an empty skill list.
     */
    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    /**
     *  Add a new skill, with the specified designation, to the skill list if it's not a duplicate.
     *
     * @param skill the skill created.
     */
    public void createSkill(Skill skill) {
        if (checkForDuplicates(skill)) {
            addSkill(skill);
        } else {
            throw new IllegalArgumentException("There is already a skill with that name.");
        }
    }

    /**
     * Checks if the skill is a duplicate of an existing skill in the repository.
     *
     * @param skill the skill to check.
     * @return true if the skill is not a duplicate, false otherwise.
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
     * Adds a skill to the skill list.
     *
     * @param skill the skill to add.
     */
    private void addSkill(Skill skill) {
        skillList.add(skill);
    }

    /**
     * Retrieves a copy of the skill list.
     *
     * @return a list of skills.
     */
    public List<Skill> getSkillList() {
        return new ArrayList<>(skillList);
    }
}
