package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.TeamDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The TeamMapper class is responsible for mapping between the Team domain object and the TeamDto data transfer object.
 * It provides methods to convert a Team object to a TeamDto object and vice versa, as well as methods to convert
 * lists of Team objects to lists of TeamDto objects and vice versa.
 */
public class TeamMapper implements Serializable {

    /**
     * Converts a Team domain object to a TeamDto data transfer object.
     *
     * @param team the Team domain object to be converted
     * @return the corresponding TeamDto data transfer object
     */
    public static TeamDto toDto(Team team) {
        return new TeamDto(team.getSkills(), team.getCollaborators(), team.getMinTeamSize(), team.getMaxTeamSize());
    }

    /**
     * Converts a TeamDto data transfer object to a Team domain object.
     *
     * @param teamDto the TeamDto data transfer object to be converted
     * @return the corresponding Team domain object
     */
    public static Team toDomain(TeamDto teamDto) {
        return new Team(teamDto.getSkills(), teamDto.getCollaboratorsDtoList(), teamDto.getMinTeamSize(), teamDto.getMaxTeamSize());
    }

    /**
     * Converts a list of TeamDto data transfer objects to a list of Team domain objects.
     *
     * @param teamDtos the list of TeamDto data transfer objects to be converted
     * @return the corresponding list of Team domain objects
     */
    public static List<Team> listToDomain(List<TeamDto> teamDtos) {
        List<Team> teamList = new ArrayList<>();
        for (TeamDto dto : teamDtos) {
            Team team = toDomain(dto);
            teamList.add(team);
        }
        return teamList;
    }

    /**
     * Converts a list of Team domain objects to a list of TeamDto data transfer objects.
     *
     * @param teamList the list of Team domain objects to be converted
     * @return the corresponding list of TeamDto data transfer objects
     */
    public static List<TeamDto> ListToDto(List<Team> teamList) {
        List<TeamDto> copy = new ArrayList<>();
        for (Team team : teamList) {
            TeamDto teamDto = TeamMapper.toDto(team);
            copy.add(teamDto);
        }
        return copy;
    }
}
