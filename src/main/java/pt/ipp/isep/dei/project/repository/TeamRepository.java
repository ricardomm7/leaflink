package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The TeamRepository class is responsible for managing the collection of Team objects.
 * It provides methods for adding teams, retrieving team data, and getting agenda entries assigned to a collaborator.
 */
public class TeamRepository implements Serializable {

    private final List<Team> teamList;

    /**
     * Constructs a new TeamRepository object and initializes the team list.
     */
    public TeamRepository() {
        this.teamList = new ArrayList<>();
    }


    /**
     * Add team.
     *
     * @param team the team
     */
    public void addTeam(Team team) {
        teamList.add(team);
    }


    /**
     * Gets team list.
     *
     * @return the team list
     */
    public List<Team> getTeamList() {
        return teamList;
    }


    public List<Team> getAvailableTeamList() {
        List<Team> availableTeam = new ArrayList<>();
        for (Team team : teamList) {
            if (team.isAvailable()) {
                availableTeam.add(team);
            }
        }
        return availableTeam;
    }


    /**
     * Remove.
     *
     * @param selectedIndex the selected index
     */
    public void remove(int selectedIndex) {
        teamList.remove(selectedIndex);
    }


    public void setTeamAvailable(Team team, Boolean flag) {
        for (Team team1 : teamList) {
            if (team.getCollaborators().equals(team1.getCollaborators()) && team.getSkills().equals(team1.getSkills()) && team.getMaxTeamSize() == team1.getMaxTeamSize() && team.getMinTeamSize() == team1.getMinTeamSize()) {
                team1.setAvailable(flag);
            }
        }
    }
}
