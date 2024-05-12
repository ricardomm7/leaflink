package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The CreateTeamController class handles the logic for creating teams.
 * It interacts with the TeamRepository and SkillRepository to retrieve skills and generate team proposals.
 */
public class CreateTeamController {
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a new CreateTeamController object.
     * Initializes the TeamRepository and SkillRepository instances.
     */
    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.skillRepository = repositories.getSkillRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
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
     * Generates a proposal for a team based on required skills, minimum and maximum team size.
     *
     * @param requiredSkills The list of skills required for the team.
     * @param minTeamSize    The minimum size of the team.
     * @param maxTeamSize    The maximum size of the team.
     * @return A list of selected collaborators for the team, or an empty list if no suitable team can be formed.
     */
    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> allCollaborators = collaboratorRepository.getCollaboratorList();
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        List<Skill> combinedSkills = new ArrayList<>();

        for (Collaborator collaborator : allCollaborators) {
            List<Skill> collaboratorSkills = collaborator.getSkills();
            for (Skill skill : collaboratorSkills) {
                if (!combinedSkills.contains(skill)) {
                    combinedSkills.add(skill);
                }
            }
            selectedCollaborators.add(collaborator);
            if (combinedSkills.containsAll(requiredSkills) && selectedCollaborators.size() >= minTeamSize) {
                teamRepository.addTeam(new Team(requiredSkills, selectedCollaborators, minTeamSize, maxTeamSize));
                return selectedCollaborators;
            }
        }

        System.out.println("Warning: Insufficient number of collaborators meeting the required skills.");
        return new ArrayList<>();
    }
}