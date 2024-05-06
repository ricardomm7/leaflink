package pt.ipp.isep.dei.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Skill> skills;
    private int minTeamSize;
    private int maxTeamSize;
    private List<Collaborator> collaborators;

    public Team(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborators = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;

    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public int getMinTeamSize() {
        return minTeamSize;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}
