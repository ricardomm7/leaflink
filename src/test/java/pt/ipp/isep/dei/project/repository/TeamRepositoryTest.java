package pt.ipp.isep.dei.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamRepositoryTest {

    @Test
    void addTeam() {
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        TeamRepository teamRepository = new TeamRepository();

        teamRepository.addTeam(team);

        List<Team> teamList = teamRepository.getTeamList();

        assertTrue(teamList.contains(team));
    }

    @Test
    void getTeamList() {

        TeamRepository teamRepository = new TeamRepository();

        List<Team> initialTeamList = teamRepository.getTeamList();

        assertTrue(initialTeamList.isEmpty());

        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        teamRepository.addTeam(team);

        List<Team> updatedTeamList = teamRepository.getTeamList();

        assertTrue(updatedTeamList.contains(team));
    }
}