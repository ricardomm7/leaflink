
package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;

import java.io.Serializable;
import java.util.List;

/**
 * The TeamDto class represents a data transfer object for the Team domain object.
 * It encapsulates the data related to a team and provides methods to access this data.
 */
public class TeamDto implements Serializable {
    private final List<Skill> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private final List<Collaborator> collaborator;

    /**
     * Constructs a new TeamDto object with the provided list of collaborators.
     * The minimum team size is set to 0, and the maximum team size is set to the size of the collaborator list.
     * The skill list is initialized as an empty list.
     *
     * @param collaborators the list of collaborators in the team
     */
    public TeamDto(List<Collaborator> collaborators) {
        this.collaborator = collaborators;
        this.skills = List.of();
        this.minTeamSize = 0;
        this.maxTeamSize = collaborators.size();
    }

    /**
     * Constructs a new TeamDto object with the provided data.
     *
     * @param skills        the list of skills required for the team
     * @param collaborators the list of collaborators in the team
     * @param minTeamSize   the minimum size of the team
     * @param maxTeamSize   the maximum size of the team
     */
    public TeamDto(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborator = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Gets the list of skills required for the team.
     *
     * @return the list of skills required for the team
     */
    public List<Skill> getSkills() {
        return (skills);
    }

    /**
     * Gets the list of collaborators in the team.
     *
     * @return the list of collaborators in the team
     */
    public List<Collaborator> getCollaboratorsDtoList() {
        return collaborator;
    }

    /**
     * Gets the minimum size of the team.
     *
     * @return the minimum size of the team
     */
    public int getMinTeamSize() {
        return minTeamSize;
    }

    /**
     * Gets the maximum size of the team.
     *
     * @return the maximum size of the team
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}
