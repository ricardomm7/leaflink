package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
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

    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> collaborators = teamRepository.getCollaborators();
        List<Collaborator> selectedCollaborators = new ArrayList<>();

        for (Collaborator collaborator : collaborators) {
            if (meetsRequiredSkills(collaborator, requiredSkills)) {
                selectedCollaborators.add(collaborator);
            }

            // Debugging output to print skills
            System.out.println("Collaborator: " + collaborator.getName() + ", Skills: " + collaborator.getSkills());

            if (selectedCollaborators.size() >= maxTeamSize) {
                break;
            }
        }

        if (selectedCollaborators.size() < minTeamSize) {
            System.out.println("Warning: Insufficient number of collaborators meeting the required skills.");
            return new ArrayList<>(); // Return an empty list to indicate no valid team proposal
        }

        System.out.println("Team Proposal:");
        for (Collaborator collaborator : selectedCollaborators) {
            System.out.println(collaborator.getName());
        }

        return selectedCollaborators;
    }

    /**
     * Checks if a collaborator meets the required skills for a team.
     *
     * @param collaborator   the collaborator to check
     * @param requiredSkills the skills required for the team
     * @return true if the collaborator meets the required skills, false otherwise
     */
    private boolean meetsRequiredSkills(Collaborator collaborator, List<Skill> requiredSkills) {
        List<Skill> collaboratorSkills = collaborator.getSkills();
        return collaboratorSkills.containsAll(requiredSkills);
    }
}

