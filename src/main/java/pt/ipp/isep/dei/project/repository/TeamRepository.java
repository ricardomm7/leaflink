package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.domain.Skill;


import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    private List<Team> teamList;

    public TeamRepository() {
        this.teamList = new ArrayList<>();
    }

    public void createTeam(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
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
    }
    public List<Collaborator> getCollaborators() {
        // Implement method to fetch collaborators from data source (e.g., database)
        // Return the list of collaborators fetched
        return new ArrayList<>(); // Placeholder return value
    }

    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        // Implement logic to select collaborators based on required skills
        // Ensure the selected collaborators meet the team size constraints
        return selectedCollaborators;
    }

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
