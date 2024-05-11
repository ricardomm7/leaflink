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

    public List<Collaborator> getCollaborators() {

        return new ArrayList<>();
    }

    public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        List<Collaborator> allCollaborators = getCollaborators();
        for (Collaborator collaborator : allCollaborators) {
            if (meetsRequiredSkills(collaborator, requiredSkills)) {
                selectedCollaborators.add(collaborator);
                if (selectedCollaborators.size() == maxTeamSize) {
                    break;
                }
            }
        }
        if (selectedCollaborators.size() < minTeamSize) {
            System.out.println("Warning: Insufficient number of collaborators meeting the required skills.");
        }
        return selectedCollaborators;
    }

    private boolean meetsRequiredSkills(Collaborator collaborator, List<Skill> requiredSkills) {
        List<Skill> collaboratorSkills = collaborator.getSkills();
        return collaboratorSkills.containsAll(requiredSkills);
    }
}
