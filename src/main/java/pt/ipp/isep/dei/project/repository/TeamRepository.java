package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private final List<Team> teamList;

    public TeamRepository() {
        this.teamList = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public List<Team> getTeamList() {
        return new ArrayList<>(teamList);
    }
}