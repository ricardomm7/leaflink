package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.TeamMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamRepositoryTest {

    @Test
    void addTeam() {
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        TeamRepository teamRepository = new TeamRepository();

        teamRepository.addTeam((team));

        List<TeamDto> teamList = TeamMapper.ListToDto(teamRepository.getTeamList());

        assertTrue(teamList.contains(TeamMapper.toDto(team)));
    }

    @Test
    void getTeamList() {

        TeamRepository teamRepository = new TeamRepository();

        List<TeamDto> initialTeamList = TeamMapper.ListToDto(teamRepository.getTeamList());

        assertTrue(initialTeamList.isEmpty());

        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        teamRepository.addTeam((team));

        List<TeamDto> updatedTeamList = TeamMapper.ListToDto(teamRepository.getTeamList());

        assertTrue(updatedTeamList.contains(TeamMapper.toDto(team)));
    }
}