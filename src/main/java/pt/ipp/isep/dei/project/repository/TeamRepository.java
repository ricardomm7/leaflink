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

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public List<Collaborator> generateProposal(List<Collaborator> collaborators, List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        // Implement logic to select collaborators with combined skills to meet the required skills, within minTeamSize and maxTeamSize
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        for (Collaborator collaborator : collaborators) {
            if (meetsCombinedSkills(selectedCollaborators, collaborator, requiredSkills) && selectedCollaborators.size() < maxTeamSize) {
                selectedCollaborators.add(collaborator);
            }
        }
        return selectedCollaborators;
    }

    private boolean meetsCombinedSkills(List<Collaborator> selectedCollaborators, Collaborator newCollaborator, List<Skill> requiredSkills) {
        List<Skill> combinedSkills = new ArrayList<>();
        for (Collaborator collaborator : selectedCollaborators) {
            combinedSkills.addAll(collaborator.getSkills());
        }
        combinedSkills.addAll(newCollaborator.getSkills());
        for (Skill requiredSkill : requiredSkills) {
            if (!combinedSkills.contains(requiredSkill)) {
                return false;
            }
        }
        return true;
    }


}
