package pt.ipp.isep.dei.project.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The Team class represents a team of collaborators working together.
 */
public class Team implements Serializable {
    private List<Skill> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private List<Collaborator> collaborators;
    private boolean isAvailable;


    /**
     * Constructs a team with the given skills, collaborators, minimum and maximum team size.
     *
     * @param skills        The list of skills possessed by the team.
     * @param collaborators The list of collaborators in the team.
     * @param minTeamSize   The minimum size of the team.
     * @param maxTeamSize   The maximum size of the team.
     */
    public Team(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize) {
        this.isAvailable = true;
        this.skills = skills;
        this.collaborators = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Constructs a team with the given skills, collaborators, minimum and maximum team size, and availability flag.
     *
     * @param skills        The list of skills possessed by the team.
     * @param collaborators The list of collaborators in the team.
     * @param minTeamSize   The minimum size of the team.
     * @param maxTeamSize   The maximum size of the team.
     * @param flag          The availability flag of the team.
     */
    public Team(List<Skill> skills, List<Collaborator> collaborators, int minTeamSize, int maxTeamSize, boolean flag) {
        this.isAvailable = flag;
        this.skills = skills;
        this.collaborators = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Constructs a team with the given minimum and maximum team size.
     *
     * @param minTeamSize The minimum size of the team.
     * @param maxTeamSize The maximum size of the team.
     */
    public Team(int minTeamSize, int maxTeamSize) {
        this.isAvailable = true;
        this.skills = null;
        this.collaborators = null;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    /**
     * Checks if the team is available.
     *
     * @return true if the team is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
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

    /**
     * Sets the availability status of the team.
     *
     * @param available The availability status to set.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Sets the collaborators of the team.
     *
     * @param collaboratorDtos The list of collaborators to set.
     */
    public void setCollaborators(List<Collaborator> collaboratorDtos) {
        this.collaborators = collaboratorDtos;
    }

    /**
     * Sets the skills of the team.
     *
     * @param skillDtos The list of skills to set.
     */
    public void setSkills(List<Skill> skillDtos) {
        this.skills = skillDtos;
    }

    /**
     * Checks if this Team object is equal to another object.
     * Two Team objects are considered equal if they have the same skills, collaborators, minimum team size,
     * maximum team size, and availability status.
     *
     * @param o the object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return minTeamSize == team.minTeamSize &&
                maxTeamSize == team.maxTeamSize &&
                isAvailable == team.isAvailable &&
                (skills == team.skills) &&
                (collaborators == team.collaborators);
    }

    /**
     * Computes the hash code for this Team object.
     *
     * @return the hash code value for this Team object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(skills, minTeamSize, maxTeamSize, collaborators, isAvailable);
    }
}
