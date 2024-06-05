package pt.ipp.isep.dei.project.repository;

import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.TeamMapper;

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


    //public void addTeam(TeamDto teamDto) {
    //  Team team = TeamMapper.toDomain(teamDto);
    //teamList.add(team);
    //}

    public void addTeam(Team team) {
        teamList.add(team);
    }


    /**
     * Gets the list of TeamDto objects.
     *
     * @return the list of TeamDto objects
     */
    public List<TeamDto> getTeamDtoList() {
        return TeamMapper.ListToDto(teamList);
    }

    /**
     * Gets team list.
     *
     * @return the team list
     */
    public List<Team> getTeamList() {
        return new ArrayList<>(teamList);
    }


    /**
     * Gets team trough collaborator.
     *
     * @param clb the clb
     * @return the team trough collaborator
     */
    public Team getTeamTroughCollaborator(Collaborator clb) {
        for (Team team : teamList) {
            if (team.getCollaborators().contains(clb)) {
                return team;
            }
        }
        return null;
    }

    /**
     * Gets the list of AgendaEntryDto objects assigned to a specific collaborator.
     *
     * @param collaborator       the UserSession object representing the collaborator
     * @param agendaEntryDtoList the list of AgendaEntryDto objects to search
     * @return the list of AgendaEntryDto objects assigned to the collaborator
     */
    public List<AgendaEntryDto> getAgendaEntriesAssignedToCollaborator(UserSession collaborator, List<AgendaEntryDto> agendaEntryDtoList) {
        List<AgendaEntryDto> assignedEntries = new ArrayList<>();
        for (AgendaEntryDto toDoEntryDto : agendaEntryDtoList) {
            for (Team team : teamList) {
                if (team.getCollaborators().equals(collaborator)) {
                    assignedEntries.add(toDoEntryDto);
                }
            }
        }
        return assignedEntries;
    }

    public List<Team> getAvailableTeamList() {
        List<Team> availableTeam = new ArrayList<>();
        for (Team team : teamList) {
            if(team.isAvailable()) {
                availableTeam.add(team);
            }
        }
        return (availableTeam);
    }

    public void setTeamAvailability(int teamIndex, Boolean isAvailable) {
        teamList.get(teamIndex).setAvailable(isAvailable);
    }

    /**
     * Remove.
     *
     * @param selectedIndex the selected index
     */
    public void remove(int selectedIndex) {
        teamList.remove(selectedIndex);
    }
}
