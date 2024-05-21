package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
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

    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
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
            if (isCollaboratorAssignedToTeam(collaborator)) {
                continue;
            }
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

        return new ArrayList<>();
    }

    /**
     * Checks if a collaborator is already assigned to a team.
     *
     * @param collaborator The collaborator to check.
     * @return true if the collaborator is already assigned to a team, false otherwise.
     */
    private boolean isCollaboratorAssignedToTeam(Collaborator collaborator) {
        List<Team> teamList = teamRepository.getTeamList();
        for (Team team : teamList) {
            if (team.getCollaborators().contains(collaborator)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the team of collaborators.
     *
     * @param allCollaborators The list of collaborators to be saved as a team.
     */
    public void saveTeam(List<Collaborator> allCollaborators) {
        Team team = new Team(allCollaborators);
        teamRepository.addTeam(team);
    }

    /**
     * Retrieves the list of saved teams.
     *
     * @return The list of saved teams.
     */
    public List<Team> getSavedTeams() {
        return teamRepository.getTeamList();
    }

}