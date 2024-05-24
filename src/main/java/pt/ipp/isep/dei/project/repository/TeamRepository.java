package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.TeamMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing teams.
 */
public class TeamRepository implements Serializable {

    private final List<Team> teamList;

    /**
     * Constructor to initialize the TeamRepository with an empty team list.
     */
    public TeamRepository() {
        this.teamList = new ArrayList<>();
    }

    /**
     * Adds a team to the repository.
     *
     * @param teamDto The team to be added.
     */
    public void addTeam(TeamDto teamDto) {
        Team team = TeamMapper.toDomain(teamDto);
        teamList.add(team);
    }

    /**
     * Retrieves a list of teams from the repository.
     *
     * @return A new list containing all teams stored in the repository.
     */
    public List<TeamDto> getTeamDtoList() {
        return TeamMapper.ListToDto(teamList);
    }


}