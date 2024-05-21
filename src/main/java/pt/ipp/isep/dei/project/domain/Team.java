package pt.ipp.isep.dei.project.domain;

import java.util.List;

/**
 * The Team class represents a team composed of collaborators with specific skills and team size constraints.
 * It holds information such as the required skills, collaborators, minimum team size, and maximum team size.
 */
public class Team {
    private final List<Skill> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private final List<Collaborator> collaborators;

    /**
     * Constructs a new Team with the given list of collaborators.
     *
     * @param collaborators The list of collaborators.
     */
    public Team(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
        this.skills = List.of();
        this.minTeamSize = 0;
        this.maxTeamSize = collaborators.size();
    }

    /**
     * Constructs a new Team object with the provided skills, collaborators, and team size constraints.
     *
     * @param skills        the required skills for the team
     * @param collaborators the list of collaborators in the team
     * @param minTeamSize   the minimum team size constraint
     * @param maxTeamSize   the maximum team size constraint
     */

    public Team(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborators = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;

    }


    /**
     * Retrieves the required skills for the team.
     *
     * @return the required skills for the team
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Retrieves the list of collaborators in the team.
     *
     * @return the list of collaborators in the team
     */
    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    /**
     * Retrieves the minimum team size constraint.
     *
     * @return the minimum team size constraint
     */
    public int getMinTeamSize() {
        return minTeamSize;
    }

    /**
     * Retrieves the maximum team size constraint.
     *
     * @return the maximum team size constraint
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}
