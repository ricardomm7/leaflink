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

        teamRepository.addTeam(TeamMapper.toDto(team));

        List<TeamDto> teamList = teamRepository.getTeamDtoList();

        assertTrue(teamList.contains(TeamMapper.toDto(team)));
    }

    @Test
    void getTeamList() {

        TeamRepository teamRepository = new TeamRepository();

        List<TeamDto> initialTeamList = teamRepository.getTeamDtoList();

        assertTrue(initialTeamList.isEmpty());

        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        teamRepository.addTeam(TeamMapper.toDto(team));

        List<TeamDto> updatedTeamList = teamRepository.getTeamDtoList();

        assertTrue(updatedTeamList.contains(TeamMapper.toDto(team)));
    }
}