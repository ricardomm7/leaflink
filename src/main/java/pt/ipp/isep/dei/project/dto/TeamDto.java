package pt.ipp.isep.dei.project.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The TeamDto class represents a data transfer object for the Team domain object.
 * It encapsulates the data related to a team and provides methods to access this data.
 */
public class TeamDto implements Serializable {
    private List<SkillDto> skills;
    private final int minTeamSize;
    private final int maxTeamSize;
    private boolean isAvailable;
    private List<CollaboratorDto> collaborator;

    /**
     * Constructs a new TeamDto object with the provided list of collaborators.
     * The minimum team size is set to 0, and the maximum team size is set to the size of the collaborator list.
     * The skill list is initialized as an empty list.
     *
     * @param collaborators the list of collaborators in the team
     */
    public TeamDto(List<CollaboratorDto> collaborators) {
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
    public TeamDto(List<SkillDto> skills, List<CollaboratorDto> collaborators, int minTeamSize, int maxTeamSize) {
        this.skills = skills;
        this.collaborator = collaborators;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }
    public TeamDto(List<SkillDto> skills, List<CollaboratorDto> collaborators, int minTeamSize, int maxTeamSize, boolean flag) {
        this.isAvailable = flag;
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
    public List<SkillDto> getSkills() {
        return (skills);
    }

    /**
     * Gets the list of collaborators in the team.
     *
     * @return the list of collaborators in the team
     */
    public List<CollaboratorDto> getCollaboratorsDtoList() {
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

    /**
     * Returns a string representation of the team, listing the names of the collaborators separated by commas.
     *
     * @return a string representation of the team
     */
    public String getTeamAsString() {
        return collaborator.stream()
                .map(CollaboratorDto::getName) // Assuming Collaborator has a getName() method
                .collect(Collectors.joining(", "));
    }

    public void setCollaboratorsDtoList(List<CollaboratorDto> dtoList) {
        this.collaborator = dtoList;
    }

    public void setSkills(List<SkillDto> skills) {
        this.skills = skills;
    }
    public boolean getAvailable(){
        return isAvailable;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDto)) return false;
        TeamDto team = (TeamDto) o;
        return minTeamSize == team.minTeamSize &&
               maxTeamSize == team.maxTeamSize &&
               isAvailable == team.isAvailable &&
               (skills == team.skills) &&
               (collaborator == team.collaborator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, minTeamSize, maxTeamSize,collaborator,isAvailable);
    }
}
