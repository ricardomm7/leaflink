package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The Team class represents a team of collaborators working together.
 */
public class Team implements Serializable {
    private final List<Skill> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private final List<Collaborator> collaborators;
    private boolean isAssigned;

    /**
     * Constructs a team with the given collaborators.
     *
     * @param collaborators The list of collaborators in the team.
     */
    public Team(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
        this.skills = List.of();
        this.minTeamSize = 0;
        this.maxTeamSize = collaborators.size();
    }

    /**
     * Constructs a team with the given skills, collaborators, minimum and maximum team size.
     *
     * @param skills        The list of skills possessed by the team.
     * @param collaborators The list of collaborators in the team.
     * @param minTeamSize   The minimum size of the team.
     * @param maxTeamSize   The maximum size of the team.
     */
    public Team(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborators = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Gets the skills of the team.
     *
     * @return The list of skills possessed by the team.
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Gets the collaborators in the team.
     *
     * @return The list of collaborators in the team.
     */
    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    /**
     * Gets the minimum size of the team.
     *
     * @return The minimum size of the team.
     */
    public int getMinTeamSize() {
        return minTeamSize;
    }

    /**
     * Gets the maximum size of the team.
     *
     * @return The maximum size of the team.
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setAvailable(Boolean flag){
        this.isAssigned = flag;
    }
}
