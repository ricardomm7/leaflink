package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;

import java.io.Serializable;
import java.util.List;

public class TeamDto implements Serializable {
    private final List<Skill> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private final List<Collaborator> collaborator;

    /**
     * Constructs a new Team with the given list of collaboratorDtos.
     *
     * @param collaborators The list of collaboratorDtos.
     */
    public TeamDto(List<Collaborator> collaborators) {
        this.collaborator = collaborators;
        this.skills = List.of();
        this.minTeamSize = 0;
        this.maxTeamSize = collaborators.size();
    }

    /**
     * Constructs a new Team object with the provided skills, collaboratorDtos, and team size constraints.
     *
     * @param skills        the required skills for the team
     * @param collaborators the list of collaboratorDtos in the team
     * @param minTeamSize   the minimum team size constraint
     * @param maxTeamSize   the maximum team size constraint
     */

    public TeamDto(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborator = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;

    }


    /**
     * Retrieves the required skills for the team.
     *
     * @return the required skills for the team
     */
    public List<Skill> getSkills() {
        return (skills);
    }

    /**
     * Retrieves the list of collaboratorDtos in the team.
     *
     * @return the list of collaboratorDtos in the team
     */
    public List<Collaborator> getCollaboratorsDtoList() {
        return collaborator;
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
