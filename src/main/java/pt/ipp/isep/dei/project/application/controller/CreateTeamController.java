package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.List;

/**
 * The CreateTeamController class handles the logic for creating teams.
 * It interacts with the TeamRepository and SkillRepository to retrieve skills and generate team proposals.
 */
public class CreateTeamController {
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;

    /**
     * Constructs a new CreateTeamController object.
     * Initializes the TeamRepository and SkillRepository instances.
     */
    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.skillRepository = repositories.getSkillRepository();
    }

    /**
     * Retrieves the list of skills from the repository.
     *
     * @return the list of skills
     */
    public List<Skill> getSkills() {
        return skillRepository.getSkillList();
    }

    /**
     * Generates a proposal of collaborators for a team based on required skills and team size constraints.
     *
     * @param requiredSkills the skills required for the team
     * @param minTeamSize    the minimum team size constraint
     * @param maxTeamSize    the maximum team size constraint
     * @return the list of selected collaborators for the team proposal
     */
    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> collaborators = teamRepository.getCollaborators(); // Assuming this method exists to fetch collaborators
        return teamRepository.generateProposal(requiredSkills, minTeamSize, maxTeamSize);
    }
}
