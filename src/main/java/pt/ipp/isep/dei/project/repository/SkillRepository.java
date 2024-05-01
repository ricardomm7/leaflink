package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Job;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    private final List<Skill> skillList;

    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    public void createSkill (String designation){
        Skill skill = new Skill(designation);
        if (checkForDuplicates(skill)) {
            addSkill(skill);
        }
    }

    private boolean checkForDuplicates(Skill skill) {
        for (Skill x : skillList) {
            if (x.getDesignation().equalsIgnoreCase(skill.getDesignation())) {
                return false;
            }
        }
        return true;
    }

    private void addSkill(Skill skill) {
        skillList.add(skill);
    }

    public List<Skill> getSkillList() {
        return new ArrayList<>(skillList);
    }
}
