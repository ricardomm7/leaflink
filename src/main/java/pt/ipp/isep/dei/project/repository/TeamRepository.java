package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.domain.Skill;


import java.util.ArrayList;
import java.util.List;


public class TeamRepository {

    public TeamRepository() {
        List<Team> teamList = new ArrayList<>();
    }

    /*public void createTeam(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        Team team = new Team(skills, collaborators, minTeamSize, maxTeamSize);
        teamList.add(team);
    }

    private boolean validateTeam(Team team) {
        int teamSize = team.getCollaborators().size();
        if (teamSize < team.getMinTeamSize() || teamSize > team.getMaxTeamSize()) {
            return false;
        }
        // Additional validation logic can be added here
        return true;
    }*/

    /**
     * Retrieves the list of collaborators.
     *
     * @return the list of collaborators
     */
    public List<Collaborator> getCollaborators() {
        // Implement method to fetch collaborators from data source (e.g., database)
        // Return the list of collaborators fetched
        return new ArrayList<>(); // Placeholder return value
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
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        // Implement logic to select collaborators based on required skills
        // Ensure the selected collaborators meet the team size constraints
        return selectedCollaborators;
    }

    /**
     * Checks if a collaborator meets the required skills for a team.
     *
     * @param collaborator    the collaborator to check
     * @param requiredSkills  the skills required for the team
     * @return true if the collaborator meets the required skills, false otherwise
     */
    private boolean meetsRequiredSkills(Collaborator collaborator, List<Skill> requiredSkills) {
        List<Skill> collaboratorSkills = collaborator.getSkills();
        for (Skill requiredSkill : requiredSkills) {
            if (!collaboratorSkills.contains(requiredSkill)) {
                return false;
            }
        }
        return true;
    }

}
