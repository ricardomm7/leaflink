package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing teams.
 */
public class TeamRepository {

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
     * @param team The team to be added.
     */
    public void addTeam(Team team) {
        teamList.add(team);
    }

    /**
     * Retrieves a list of teams from the repository.
     *
     * @return A new list containing all teams stored in the repository.
     */
    public List<Team> getTeamList() {
        return new ArrayList<>(teamList);
    }
}