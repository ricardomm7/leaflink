package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamController {
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;

    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.skillRepository = repositories.getSkillRepository();
    }

    public List<Skill> getSkills() {
        return skillRepository.getSkillList();
    }

    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        // Retrieve collaborators from the repository or any other source
        List<Collaborator> collaborators = teamRepository.getCollaborators(); // Assuming this method exists to fetch collaborators
        // Call the generateProposal method of the TeamRepository passing the required arguments
        return teamRepository.generateProposal(requiredSkills, minTeamSize, maxTeamSize);
    }
}
